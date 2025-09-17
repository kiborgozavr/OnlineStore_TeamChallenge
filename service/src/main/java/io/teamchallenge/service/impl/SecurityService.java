package io.teamchallenge.service.impl;

import io.teamchallenge.constant.ExceptionMessage;
import io.teamchallenge.dto.security.*;
import io.teamchallenge.entity.PasswordResetToken;
import io.teamchallenge.entity.User;
import io.teamchallenge.enumerated.Role;
import io.teamchallenge.exception.AlreadyExistsException;
import io.teamchallenge.exception.BadCredentialsException;
import io.teamchallenge.exception.BadTokenException;
import io.teamchallenge.exception.NotFoundException;
import io.teamchallenge.repository.PasswordResetTokenRepository;
import io.teamchallenge.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Service class for managing security.
 *
 * @author Denys Liubchenko
 */
@Service
@Slf4j
@Transactional(readOnly = true)
public class SecurityService {
    private static final String NEW_PASSWORD_BASE_URL = "https://gadget-house-tc.netlify.app/change-password?token=";

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    /**
     * Constructor for SecurityService.
     *
     * @param userRepository  The user repository.
     * @param jwtService      The JWT service.
     * @param passwordEncoder The password encoder.
     * @param modelMapper     The ModelMapper.
     */
    @Autowired
    public SecurityService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder,
                           ModelMapper modelMapper, PasswordResetTokenRepository passwordResetTokenRepository) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
    }

    /**
     * Registers a new user.
     *
     * @param signUpRequestDto The sign-up request DTO containing user details.
     * @return The sign-up response DTO containing user information.
     */
    @Transactional
    public SignUpResponseDto signUpUser(SignUpRequestDto signUpRequestDto) {
        log.info("User tries to sign up {}", signUpRequestDto);
        if (userRepository.existsByEmail(signUpRequestDto.getEmail())) {
            throw new AlreadyExistsException(
                ExceptionMessage.USER_WITH_EMAIL_ALREADY_EXISTS.formatted(signUpRequestDto.getEmail()));
        }
        if (userRepository.existsByPhoneNumber(signUpRequestDto.getPhoneNumber())) {
            throw new AlreadyExistsException(
                ExceptionMessage.USER_WITH_PHONE_NUMBER_ALREADY_EXISTS.formatted(signUpRequestDto.getPhoneNumber()));
        }
        User user = modelMapper.map(signUpRequestDto, User.class);
        return modelMapper.map(userRepository.save(user), SignUpResponseDto.class);
    }

    /**
     * Authenticates a user.
     *
     * @param signInRequestDto The sign-in request DTO containing user credentials.
     * @return The sign-in response DTO containing access and refresh tokens.
     * @throws BadCredentialsException if the provided credentials are invalid.
     * @throws NotFoundException       if the user is not found.
     */
    public SignInResponseDto signInUser(SignInRequestDto signInRequestDto) {
        log.info("User tries to sign in {}", signInRequestDto);
        User user = userRepository.findUserByEmail(signInRequestDto.getEmail())
            .orElseThrow(() -> new NotFoundException(
                ExceptionMessage.USER_NOT_FOUND_BY_EMAIL.formatted(signInRequestDto.getEmail())));
        if (!passwordEncoder.matches(signInRequestDto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException(ExceptionMessage.PASSWORD_DOES_NOT_MATCH);
        }
        String accessToken = jwtService.generateAccessToken(user.getId(), user.getEmail(), user.getRole());
        String refreshToken = jwtService.generateRefreshToken(user);
        return SignInResponseDto.builder()
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .build();
    }

    /**
     * Updates access tokens for a user based on the provided refresh token.
     *
     * @param refreshToken The refresh token used to generate new access and refresh tokens.
     * @return A {@link SignInResponseDto} containing the updated access and refresh tokens.
     * @throws BadTokenException If the provided refresh token does not contain a subject.
     * @throws NotFoundException If a user with the email extracted from the refresh token is not found.
     */
    @Transactional
    public SignInResponseDto updateAccessToken(String refreshToken) {
        String email = jwtService.getSubjectFromToken(refreshToken)
            .orElseThrow(() -> new BadTokenException(ExceptionMessage.TOKEN_DOES_NOT_CONTAIN_SUBJECT));
        User user = userRepository.findUserByEmail(email)
            .orElseThrow(() -> new NotFoundException(ExceptionMessage.USER_NOT_FOUND_BY_EMAIL.formatted(email)));
        jwtService.verifyToken(refreshToken, user.getRefreshTokenKey());

        String accessToken = jwtService.generateAccessToken(user.getId(), user.getEmail(), user.getRole());
        String newRefreshToken = jwtService.generateRefreshToken(user);
        return SignInResponseDto.builder()
            .accessToken(accessToken)
            .refreshToken(newRefreshToken)
            .build();
    }

    @Transactional
    public NewAdminResponseDto createNewAdmin(NewAdminDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new AlreadyExistsException(ExceptionMessage.USER_WITH_EMAIL_ALREADY_EXISTS.formatted(dto.getEmail()));
        }

        User admin = User.builder()
            .email(dto.getEmail())
            .fullName(dto.getFullName())
            .password(passwordEncoder.encode(dto.getPassword()))
            .role(Role.ROLE_ADMIN)
            .build();

        return modelMapper.map(userRepository.save(admin), NewAdminResponseDto.class);
    }

    @Transactional
    public String generateRecoveryLink(User user) {
        String token = jwtService.generateTokenKey();
        PasswordResetToken passwordResetToken = PasswordResetToken.builder()
            .user(user)
            .token(token)
            .expiresAt(LocalDateTime.now().plusDays(1))
            .build();
        passwordResetTokenRepository.save(passwordResetToken);
        return NEW_PASSWORD_BASE_URL + token;
    }

    public boolean isValidPasswordResetToken(String token) {
        Optional<PasswordResetToken> tokenOptional = passwordResetTokenRepository.findByToken(token);
        return (tokenOptional.isPresent() &&
            !tokenOptional.get().isUsed() &&
            tokenOptional.get().getExpiresAt().isAfter(LocalDateTime.now()));
    }

    @Transactional
    public User getUserFromPasswordResetToken(String token) {
        //TODO Make specific exception
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token).orElseThrow();
        if (passwordResetToken.isUsed()) throw new BadTokenException("Token has already been used");
        passwordResetToken.setUsed(true);
        passwordResetTokenRepository.save(passwordResetToken);
        return passwordResetToken.getUser();
    }

    public SignInResponseDto buildSignInResponseFromUser(User user) {
        String accessToken = jwtService.generateAccessToken(user.getId(), user.getEmail(), user.getRole());
        String newRefreshToken = jwtService.generateRefreshToken(user);
        return SignInResponseDto.builder()
            .accessToken(accessToken)
            .refreshToken(newRefreshToken)
            .build();
    }
}
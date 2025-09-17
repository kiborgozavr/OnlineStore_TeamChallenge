package io.teamchallenge.controller;

import io.teamchallenge.dto.security.*;
import io.teamchallenge.entity.User;
import io.teamchallenge.service.impl.SecurityService;
import io.teamchallenge.service.impl.UserService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

/**
 * Controller for security.
 * @author Denys Liubchenko
 */
@RestController
@RequestMapping("/api/v1")
@Validated
@RequiredArgsConstructor
public class SecurityController {
    private final SecurityService securityService;
    private final UserService userService;

    /**
     * Endpoint for user sign-up.
     *
     * @param signUpRequestDto Request body containing sign-up information.
     * @return SignUpResponseDto containing sign-up result.
     */
    @PostMapping("/signUp")
    public ResponseEntity<SignUpResponseDto> signUpUser(@Valid @RequestBody SignUpRequestDto signUpRequestDto) {
        return ResponseEntity.status(CREATED).body(securityService.signUpUser(signUpRequestDto));
    }

    /**
     * Endpoint for user sign-in.
     *
     * @param signInRequestDto Request body containing sign-in information.
     * @return SignInResponseDto containing sign-in result.
     */
    @PostMapping("/signIn")
    public ResponseEntity<SignInResponseDto> signInUser(@Valid @RequestBody SignInRequestDto signInRequestDto) {
        return ResponseEntity.status(OK).body(securityService.signInUser(signInRequestDto));
    }

    /**
     * Method for refresh access token.
     *
     * @param refreshToken - {@link String} this is refresh token.
     * @return {@link ResponseEntity} - with new access token.
     */
    @PostMapping("/updateAccessToken")
    public ResponseEntity<SignInResponseDto> updateAccessToken(@RequestParam @NotBlank String refreshToken) {
        return ResponseEntity.ok().body(securityService.updateAccessToken(refreshToken));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> sendLinkForPasswordReset(@RequestParam("email") String email) throws MessagingException {
        Optional<User> user = userService.getUser(email);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body("Unknown user");
        } else {
            userService.sendPasswordResetEmail(user.get());
            return ResponseEntity.accepted().body("Email with restoring link was sent");
        }
    }

    @GetMapping("/change-password")
    public ResponseEntity<?> validatePasswordResetToken(@RequestParam("token") String passwordResetToken) {
        if (securityService.isValidPasswordResetToken(passwordResetToken)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/change-password")
    public ResponseEntity<SignInResponseDto> setPasswordWithRestoringToken(@RequestBody @Valid ResetPasswordDto dto) {
        return ResponseEntity.ok().body(userService.resetPassword(dto));
    }

    @PostMapping("/create-new-admin")
    public ResponseEntity<NewAdminResponseDto> createNewAdmin(@RequestBody @Valid NewAdminDto dto) {
        return ResponseEntity.status(201).body(securityService.createNewAdmin(dto));
    }
}

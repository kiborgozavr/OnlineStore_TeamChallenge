package io.teamchallenge.controller;

import io.teamchallenge.dto.security.*;
import io.teamchallenge.entity.User;
import io.teamchallenge.service.impl.SecurityService;
import io.teamchallenge.service.impl.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
@Slf4j
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
    public ResponseEntity<?> sendLinkForPasswordReset(@RequestParam("email") String email) {
        log.debug("start reset-password endpoint");

        Optional<User> user = userService.getUser(email);
        if (user.isEmpty()) {
            log.debug("user is null");
            return ResponseEntity.badRequest().body("Unknown user");
        } else {
            log.debug("user is not null");

            userService.sendPasswordResetEmail(user.get());
            log.debug("userService.sendPasswordResetEmail(user.get()) is finished");
            return ResponseEntity.accepted().body("Email with restoring link was sent");
        }
    }

    @GetMapping("/change-password")
    public ResponseEntity<?> validatePasswordResetToken(@RequestParam("token") String passwordResetToken) {
        log.debug("start change-password endpoint");

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
    public ResponseEntity<NewAdminResponseDto> createNewAdmin(@RequestBody @Valid NewAdminRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(securityService.createNewAdmin(dto));
    }
}
package io.teamchallenge.dto.security;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ResetPasswordDto {

    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()\\-\\[\\]{}:;',?/*~$^+=<>])(?=\\S+$).{8,24}$",
            message = "Password must be 8–24 characters long, contain upper and lower case Latin letters, a number, and a special character"
    )
    private String password;

    private String token;
}


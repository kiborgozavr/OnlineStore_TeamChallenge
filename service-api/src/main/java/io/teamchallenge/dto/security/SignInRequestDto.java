package io.teamchallenge.dto.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignInRequestDto {

    @Email(message = "Please, insert valid email address")
    private String email;

    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()\\-\\[\\]{}:;',?/*~$^+=<>])(?=\\S+$).{8,24}$",
            message = "Password must be 8–24 characters long, contain upper and lower case Latin letters, a number, and a special character"
    )
    private String password;
}

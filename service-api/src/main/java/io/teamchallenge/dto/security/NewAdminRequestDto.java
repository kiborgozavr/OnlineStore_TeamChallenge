package io.teamchallenge.dto.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class NewAdminRequestDto {

    @NotBlank
    private String fullName;

    @NotBlank
    @Email(message = "Please, insert valid email address")
    private String email;

    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()\\-\\[\\]{}:;',?/*~$^+=<>])(?=\\S+$).{8,24}$",
            message = "Password must be 8–24 characters long, contain upper and lower case Latin letters, a number, and a special character"
    )
    private String password;

    @NotBlank
    @Pattern(regexp = "^0\\d{9}$",
            message = "Phone number must start with 0 and contain 9 digits in total")
    private String phoneNumber;
}

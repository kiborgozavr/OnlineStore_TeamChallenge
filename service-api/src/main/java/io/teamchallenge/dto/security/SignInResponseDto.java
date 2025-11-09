package io.teamchallenge.dto.security;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignInResponseDto {
    private String accessToken;
    private String refreshToken;
}

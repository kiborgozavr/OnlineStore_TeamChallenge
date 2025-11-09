package io.teamchallenge.dto.security;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpResponseDto {

    private Long id;

    private String email;

    private String fullName;
}

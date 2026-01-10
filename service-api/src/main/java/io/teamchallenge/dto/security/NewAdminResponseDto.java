package io.teamchallenge.dto.security;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewAdminResponseDto {

    private String message = "New Admin Created";
    private String fullName;
    private String email;
    private String phoneNumber;
}
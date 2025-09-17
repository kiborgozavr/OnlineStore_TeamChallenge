package io.teamchallenge.dto.security;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NewAdminResponseDto {

    private final String message = "New Admin Created";
    private String fullName;
    private String email;
}

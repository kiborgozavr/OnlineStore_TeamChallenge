package io.teamchallenge.dto.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class NewAdminDto {

    private String fullName;
    private String email;
    private String password;
}

package io.teamchallenge.mapper;

import io.teamchallenge.dto.security.NewAdminRequestDto;
import io.teamchallenge.entity.User;
import io.teamchallenge.enumerated.Role;
import io.teamchallenge.service.impl.JwtService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.AbstractConverter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class newAdminMapper extends AbstractConverter<NewAdminRequestDto, User> {

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    protected User convert(NewAdminRequestDto dto) {
        return User.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .phoneNumber(dto.getPhoneNumber())
                .fullName(dto.getFullName())
                .role(Role.ROLE_ADMIN)
                .refreshTokenKey(jwtService.generateTokenKey())
                .build();
    }
}
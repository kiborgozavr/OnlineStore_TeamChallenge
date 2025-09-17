package io.teamchallenge.mapper;

import io.teamchallenge.dto.security.NewAdminResponseDto;
import io.teamchallenge.entity.User;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class NewAdminMapper extends AbstractConverter<User, NewAdminResponseDto> {
    @Override
    protected NewAdminResponseDto convert(User user) {
        return NewAdminResponseDto.builder()
            .email(user.getEmail())
            .fullName(user.getFullName())
            .build();
    }
}

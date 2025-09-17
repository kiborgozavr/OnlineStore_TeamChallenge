package io.teamchallenge.mapper;

import io.teamchallenge.dto.address.AddressDto;
import io.teamchallenge.dto.user.UserProfile;
import io.teamchallenge.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserProfileMapper extends AbstractConverter<User, UserProfile> {

    private final OrderResponseDtoMapper orderResponseDtoMapper;

    @Override
    protected UserProfile convert(User source) {
        return UserProfile.builder()
                .id(source.getId())
                .fullName(source.getFullName())
                .phoneNumber(source.getPhoneNumber())
                .secondaryPhoneNumber(source.getSecondaryPhoneNumber())
                .birthdate(source.getBirthdate())
                .address(AddressDto.builder()
                        .city(source.getAddress() == null ? null : source.getAddress().getCity())
                        .addressLine(source.getAddress() == null ? null : source.getAddress().getAddressLine())
                        .build())
                .birthdate(source.getBirthdate())
                .email(source.getEmail())
                .role(source.getRole())
                .sex(source.getSex())
                .orders(source.getOrders().stream()
                        .map(orderResponseDtoMapper::convert)
                        .collect(Collectors.toSet()))
                .build();
    }
}

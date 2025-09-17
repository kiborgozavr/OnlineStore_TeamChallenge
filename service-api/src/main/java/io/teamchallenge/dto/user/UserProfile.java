package io.teamchallenge.dto.user;

import io.teamchallenge.dto.address.AddressDto;
import io.teamchallenge.dto.order.OrderRequestDto;
import io.teamchallenge.dto.order.OrderResponseDto;
import io.teamchallenge.enumerated.Role;
import io.teamchallenge.enumerated.Sex;
import lombok.*;

import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class UserProfile {
    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String secondaryPhoneNumber;
    private Date birthdate;
    private Sex sex;
    private AddressDto address;
    private Role role;
    private Set<OrderResponseDto> orders;
}

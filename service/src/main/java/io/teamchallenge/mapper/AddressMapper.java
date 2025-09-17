package io.teamchallenge.mapper;

import io.teamchallenge.dto.address.AddressDto;
import io.teamchallenge.entity.Address;
import lombok.RequiredArgsConstructor;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mapper for {@link AddressDto}.
 *
 * @author Denys Liubchenko
 */
@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AddressMapper extends AbstractConverter<AddressDto, Address> {

    /**
     * Converts an {@link AddressDto} object to an {@link Address} entity.
     *
     * @param addressDto {@link AddressDto} object to be converted.
     * @return {@link Address} entity.
     */
    @Override
    protected Address convert(AddressDto addressDto) {
        StringBuilder addressLine = new StringBuilder();
        if (addressDto.getStreet() != null) {
            addressLine.append(addressDto.getStreet());
        }
        if (addressDto.getHouseNumber() != null) {
            addressLine.append(", ").append(addressDto.getHouseNumber());
        }
        if (addressDto.getFlat() != null) {
            addressLine.append(", ").append(addressDto.getFlat());
        }
        if (addressDto.getCity() != null) {
            addressLine.append(", ").append(addressDto.getCity());
        }
        if (addressDto.getDepartmentNumber() != null) {
            addressLine.append(", ").append(addressDto.getDepartmentNumber());
        }
        if (addressLine.isEmpty()) {
            addressLine.append("Unknown address");
        }
        if (addressLine.charAt(0) == ',') {
            addressLine.deleteCharAt(0);
        }

        return Address.builder()
                .addressLine(addressLine.toString().trim())
                .city(addressDto.getCity())
                .street(addressDto.getStreet())
                .houseNumber(addressDto.getHouseNumber())
                .flatNumber(addressDto.getFlat())
                .departmentNumber(addressDto.getDepartmentNumber())
                .build();
    }
}

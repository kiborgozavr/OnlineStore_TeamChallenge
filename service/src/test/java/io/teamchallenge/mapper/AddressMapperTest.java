package io.teamchallenge.mapper;

import io.teamchallenge.dto.address.AddressDto;
import io.teamchallenge.entity.Address;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static io.teamchallenge.util.Utils.getAddressDto;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AddressMapperTest {
    @InjectMocks
    private AddressMapper addressMapper;


    @Test
    void convertTest() {
        AddressDto addressDto = getAddressDto();
        Address expected = Address.builder()
                .addressLine(addressDto.getAddressLine())
                .city(addressDto.getCity())
                .build();

        Address convert = addressMapper.convert(addressDto);

        assertEquals(expected, convert);
    }
}

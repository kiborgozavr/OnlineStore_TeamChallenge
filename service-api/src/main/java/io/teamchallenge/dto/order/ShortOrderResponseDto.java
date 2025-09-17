package io.teamchallenge.dto.order;

import io.teamchallenge.dto.address.AddressDto;
import io.teamchallenge.enumerated.DeliveryMethod;
import io.teamchallenge.enumerated.DeliveryStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class ShortOrderResponseDto {
    private Long id;
    private String email;
    private String fullName;
    private String phoneNumber;
    private DeliveryMethod deliveryMethod;
    private DeliveryStatus deliveryStatus;
    private AddressDto address;
    private Boolean isPaid;
    private LocalDateTime createdAt;
    private BigDecimal total;
}

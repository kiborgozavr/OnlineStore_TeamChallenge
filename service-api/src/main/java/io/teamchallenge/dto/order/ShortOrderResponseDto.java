package io.teamchallenge.dto.order;

import io.teamchallenge.dto.address.AddressDto;
import io.teamchallenge.enumerated.DeliveryStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShortOrderResponseDto {
    private Long id;

    private String fullName;
    private String email;
    private String phoneNumber;
    private AddressDto address;

    private String paymentMethod;
    private Boolean isPaid;
    private String deliveryMethod;
    private DeliveryStatus deliveryStatus;

    private LocalDateTime createdAt;
    private BigDecimal total;
}

package io.teamchallenge.dto.order;

import io.teamchallenge.dto.address.AddressDto;
import io.teamchallenge.enumerated.DeliveryMethod;
import io.teamchallenge.enumerated.DeliveryStatus;
import io.teamchallenge.enumerated.PaymentMethod;
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

    private String fullName;
    private String email;
    private String phoneNumber;
    private AddressDto address;

    private PaymentMethod paymentMethod;
    private Boolean isPaid;
    private DeliveryMethod deliveryMethod;
    private DeliveryStatus deliveryStatus;

    private LocalDateTime createdAt;
    private BigDecimal total;
}

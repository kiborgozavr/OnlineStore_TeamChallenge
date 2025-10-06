package io.teamchallenge.dto.order;

import io.teamchallenge.dto.address.AddressDto;
import io.teamchallenge.dto.user.UserVO;
import io.teamchallenge.enumerated.DeliveryMethod;
import io.teamchallenge.enumerated.DeliveryStatus;
import io.teamchallenge.enumerated.PaymentMethod;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseDto {
    private Long id;

    private String fullName;
    private String email;
    private String phoneNumber;
    private AddressDto address;

    private List<OrderItemResponseDto> orderItems;

    private PaymentMethod paymentMethod;
    private Boolean isPaid;
    private DeliveryMethod deliveryMethod;
    private DeliveryStatus deliveryStatus;

    private String comment;

    private LocalDateTime createdAt;

    private BigDecimal total;
    private UserVO user;
}

package io.teamchallenge.dto.order;

import io.teamchallenge.dto.address.AddressDto;
import io.teamchallenge.dto.user.UserVO;
import io.teamchallenge.enumerated.DeliveryStatus;
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

    private String paymentMethod;
    private Boolean isPaid;
    private String deliveryMethod;
    private DeliveryStatus deliveryStatus;

    private String comment;

    private LocalDateTime createdAt;

    private BigDecimal total;
    private UserVO user;
}

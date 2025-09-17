package io.teamchallenge.mapper;

import io.teamchallenge.dto.order.ShortOrderResponseDto;
import io.teamchallenge.entity.Order;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShortOrderResponseDtoMapper extends AbstractConverter<Order, ShortOrderResponseDto> {
    private final AddressDtoMapper addressDtoMapper;

    @Override
    protected ShortOrderResponseDto convert(Order source) {
        ShortOrderResponseDto orderResponseDto = ShortOrderResponseDto.builder()
            .id(source.getId())
            .email(source.getContactInfo().getEmail())
            .fullName(source.getContactInfo().getFullName())
            .phoneNumber(source.getContactInfo().getPhoneNumber())
            .deliveryMethod(source.getDeliveryMethod())
            .deliveryStatus(source.getDeliveryStatus())
            .isPaid(source.getIsPaid())
            .createdAt(source.getCreatedAt())
            .total(source.getOrderItems().stream()
                .map(orderItem -> orderItem.getPrice()
                    .multiply(BigDecimal.valueOf(orderItem.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add))
            .build();
        if (source.getAddress() != null) {
            orderResponseDto.setAddress(addressDtoMapper.convert(source.getAddress()));
        }
        return orderResponseDto;
    }
}


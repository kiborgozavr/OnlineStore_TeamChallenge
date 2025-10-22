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

                .fullName(source.getContactInfo().getFullName())
                .email(source.getContactInfo().getEmail())
                .phoneNumber(source.getContactInfo().getPhoneNumber())

                .paymentMethod(source.getPaymentMethod())
                .isPaid(source.getIsPaid())
                .deliveryMethod(source.getDeliveryMethod())
                .deliveryStatus(source.getDeliveryStatus())

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


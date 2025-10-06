package io.teamchallenge.mapper;

import io.teamchallenge.dto.order.OrderItemResponseDto;
import io.teamchallenge.dto.order.OrderResponseDto;
import io.teamchallenge.entity.Order;

import java.math.BigDecimal;

import lombok.RequiredArgsConstructor;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderResponseDtoMapper extends AbstractConverter<Order, OrderResponseDto> {
    private final ShortProductResponseDtoMapper shortProductResponseDtoMapper;
    private final AddressDtoMapper addressDtoMapper;

    @Override
    protected OrderResponseDto convert(Order source) {
        OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                .id(source.getId())
                .fullName(source.getContactInfo().getFullName())
                .email(source.getContactInfo().getEmail())
                .phoneNumber(source.getContactInfo().getPhoneNumber())
                .paymentMethod(source.getPaymentMethod())
                .isPaid(source.getIsPaid())
                .deliveryMethod(source.getDeliveryMethod())
                .deliveryStatus(source.getDeliveryStatus()).orderItems(source.getOrderItems().stream().map(orderItem -> OrderItemResponseDto.builder()
                        .quantity(orderItem.getQuantity())
                        .price(orderItem.getPrice())
                        .shortProductResponseDto(shortProductResponseDtoMapper.convert(orderItem.getProduct()))
                        .build()).toList())
                .createdAt(source.getCreatedAt())
                .total(source.getOrderItems().stream()
                        .map(orderItem -> orderItem.getPrice()
                                .multiply(BigDecimal.valueOf(orderItem.getQuantity())))
                        .reduce(BigDecimal.ZERO, BigDecimal::add))
                .comment(source.getComment())
                .build();
        if (source.getAddress() != null) {
            orderResponseDto.setAddress(addressDtoMapper.convert(source.getAddress()));
        }
        return orderResponseDto;
    }
}

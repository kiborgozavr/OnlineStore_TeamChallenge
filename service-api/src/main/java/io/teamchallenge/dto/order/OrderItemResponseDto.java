package io.teamchallenge.dto.order;

import io.teamchallenge.dto.product.ShortProductResponseDto;

import java.math.BigDecimal;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class OrderItemResponseDto {
    private ShortProductResponseDto shortProductResponseDto;
    private Integer quantity;
    private BigDecimal price;
}

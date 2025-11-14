package io.teamchallenge.dto.product;

import io.teamchallenge.dto.ImageDto;
import io.teamchallenge.dto.category.CategoryResponseDto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDto {

    private Long id;
    private String shortDesc;
    private CategoryResponseDto categoryResponseDto;
    private List<ProductAttributeResponseDto> productAttributeResponseDtos;
    private List<ImageDto> images;
    private String brand;
    private String name;
    private String href;
    private String description;
    private String code;
    private BigDecimal price;
    private Integer quantity;
    private Double rating;
    private HashMap<String, List<AlternativeProductDto>> alternativeProducts;
    private LocalDateTime createdAt;
}

package io.teamchallenge.mapper;

import io.teamchallenge.dto.ImageDto;
import io.teamchallenge.dto.category.CategoryResponseDto;
import io.teamchallenge.dto.product.AlternativeProductDto;
import io.teamchallenge.dto.product.ProductAttributeResponseDto;
import io.teamchallenge.dto.product.ProductResponseDto;
import io.teamchallenge.entity.AlternativeProduct;
import io.teamchallenge.entity.Product;
import io.teamchallenge.entity.attributes.AttributeValue;
import io.teamchallenge.entity.reviews.Review;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for {@link Product}.
 *
 * @author Niktia Malov
 */

@Component
public class ProductResponseDtoMapper extends AbstractConverter<Product, ProductResponseDto> {

    private final String COLOR = "color";

    /**
     * Converts a Product entity to a corresponding ProductResponseDto object.
     *
     * @param product The Product entity to be converted.
     * @return ProductResponseDto representing the converted product with relevant information.
     */
    @Override
    protected ProductResponseDto convert(Product product) {
        return ProductResponseDto.builder()
            .id(product.getId())
            .shortDesc(product.getShortDesc())
            .categoryResponseDto(
                CategoryResponseDto.builder()
                    .id(product.getCategory().getId())
                    .description(product.getCategory().getDescription())
                    .name(product.getCategory().getName())
                    .build())
            .productAttributeResponseDtos(product.getProductAttributes()
                .stream()
                .map((pa) ->
                    ProductAttributeResponseDto
                        .builder()
                        .name(pa.getAttributeValue().getAttribute().getName())
                        .value(pa.getAttributeValue().getValue())
                        .build())
                .collect(Collectors.toList()))
            .images(product.getImages()
                .stream()
                .map(img -> ImageDto.builder()
                    .link(img.getLink())
                    .order(img.getOrder())
                    .build())
                .collect(Collectors.toList()))
            .brand(product.getBrand().getName())
            .name(product.getName())
            .href(product.getName().toLowerCase()
                    .replaceAll("[^a-z0-9]+", "-")
                    .replaceAll("^-+|-+$", ""))
            .description(product.getDescription())
            .code(product.getCode())
            .price(product.getPrice())
            .quantity(product.getQuantity())
            .createdAt(product.getCreatedAt())
            .alternativeProducts(getAlternativeProducts(product))
            .rating(roundedRating(
                    product.getReviews().stream()
                .mapToInt(Review::getRate)
                .average()
                .orElse(3.0))) //TODO
            .build();
    }

    private double roundedRating(double rating) {
        return (rating == 0) ? 0.0 : Math.round(rating * 2) / 2.0;
    } //TODO

    private HashMap<String, List<AlternativeProductDto>> getAlternativeProducts(Product product) {

        HashMap<String, List<AlternativeProductDto>> alternativeProducts = new HashMap<>();

        for (AlternativeProduct alternativeProduct : product.getAlternativeProducts()) {

            String attribute = alternativeProduct.getAttributeValue().getAttribute().getName();
            attribute = attribute.replaceAll(" ", "")
                    .replaceFirst(String.valueOf(attribute.charAt(0)), String.valueOf(attribute.charAt(0)).toLowerCase());

            AlternativeProductDto alternativeProductDto = AlternativeProductDto.builder()
                    .productId(alternativeProduct.getAlternativeProduct().getId())
                    .attributeValue(alternativeProduct.getAlternativeAttributeValue().getValue()
                            .replaceAll(" ", ""))
                    .isAvailable(alternativeProduct.getAlternativeProduct().getQuantity() > 0)
                    .categoryId(alternativeProduct.getAlternativeProduct().getCategory().getId())
                    .href(alternativeProduct.getAlternativeProduct().getName().toLowerCase()
                            .replaceAll("[^a-z0-9]+", "-")
                            .replaceAll("^-+|-+$", ""))
                    .build();

            alternativeProducts.computeIfAbsent(attribute, o -> {
                List<AlternativeProductDto> list = new ArrayList<>();
                list.add(AlternativeProductDto.builder()
                        .productId(product.getId())
                        .categoryId(product.getCategory().getId())
                        .isAvailable(product.getQuantity() > 0)
                        .attributeValue(alternativeProduct.getAttributeValue().getValue().replaceAll(" ", ""))
                        .href(product.getName().toLowerCase()
                                .replaceAll("[^a-z0-9]+", "-")
                                .replaceAll("^-+|-+$", ""))
                        .build());
                return list;
            });
            alternativeProducts.get(attribute).add(alternativeProductDto);
        }

        AlternativeProductDto thisProductColor = AlternativeProductDto.builder()
                .productId(product.getId())
                    .categoryId(product.getCategory().getId())
                        .isAvailable(product.getQuantity() > 0)
                            .attributeValue(product.getColor().getHex())
            .href(product.getName().toLowerCase()
                .replaceAll("[^a-z0-9]+", "-")
                .replaceAll("^-+|-+$", ""))
            .build();

        alternativeProducts.putIfAbsent(COLOR, List.of(thisProductColor));
        return alternativeProducts;
    }
}

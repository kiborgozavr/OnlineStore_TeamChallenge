package io.teamchallenge.mapper;

import io.teamchallenge.dto.ImageDto;
import io.teamchallenge.dto.product.ShortProductResponseDto;
import io.teamchallenge.entity.Product;
import io.teamchallenge.entity.reviews.Review;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * Mapper for {@link Product}.
 *
 * @author Niktia Malov
 */
@Component
public class ShortProductResponseDtoMapper extends AbstractConverter<Product, ShortProductResponseDto> {
    /**
     * Converts a Product entity to a ShortProductResponseDto.
     *
     * @param product The Product entity to be converted.
     * @return A ShortProductResponseDto representing the converted product.
     */
    @Override
    protected ShortProductResponseDto convert(Product product) {
        return ShortProductResponseDto.builder()
            .id(product.getId())
            .name(product.getName())
            .href(product.getName().toLowerCase()
                    .replaceAll("[^a-z0-9]+", "-")
                    .replaceAll("^-+|-+$", ""))
            .price(product.getPrice())
            .images(product.getImages()
                .stream()
                .map(img -> ImageDto.builder()
                    .link(img.getLink())
                    .order(img.getOrder())
                    .build())
                .collect(Collectors.toList()))
            .available(product.getQuantity() > 0)
            .code(product.getCode())
            .categoryId(product.getCategory().getId())
            .rating(roundedRating(
                    product.getReviews().stream()
                .mapToInt(Review::getRate)
                .average()
                .orElse(3.0))) // TODO
            .build();
    }

    private double roundedRating(double rating) {
        return (rating == 0) ? 0.0 : Math.round(rating * 2) / 2.0;
    } //TODO
}

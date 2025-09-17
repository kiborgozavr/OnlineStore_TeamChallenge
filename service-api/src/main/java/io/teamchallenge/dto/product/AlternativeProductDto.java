package io.teamchallenge.dto.product;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@EqualsAndHashCode
public class AlternativeProductDto {

    private Long productId;
    private String attributeValue;
    private String href;
    private Long categoryId;
    private boolean isAvailable;
}

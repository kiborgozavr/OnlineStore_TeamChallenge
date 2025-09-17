package io.teamchallenge.entity;

import io.teamchallenge.entity.attributes.AttributeValue;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "alternative_products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlternativeProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attribute_value_id", nullable = false)
    private AttributeValue attributeValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alternative_product_id")
    private Product alternativeProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alternative_attribute_value_id", nullable = false)
    private AttributeValue alternativeAttributeValue;
}

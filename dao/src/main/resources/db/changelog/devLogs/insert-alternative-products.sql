-- liquibase formatted sql
-- changeset Kate Volianiuk:insert-alternative-products
insert into alternative_products(product_id, alternative_product_id, attribute_value_id, alternative_attribute_value_id)
values
--Apple iPhone 15 Pro Max 256Gb Natural Titanium
(1, 4, 107, 106),
(1, 21, 107, 108),

--Apple iPhone 15 Pro 256Gb Blue Titanium
(4, 22, 9, 8),
(4, 21, 106, 108),
(4, 23, 2, 3),
(4, 1, 108, 107),

--Apple iPhone 15 128GB Green
(6, 7, 11, 13),
(6, 8, 108, 129),
(6, 21, 1, 2),

--Apple iPhone 15 128GB Pink
(7, 6, 13, 11),
(7, 8, 108, 129),
(7, 21, 1, 2),

--Apple iPhone 13 128Gb Starlight
(8, 6, 129, 108),
(8, 24, 16, 16),
(8, 25, 16, 85),
(8, 26, 16, 13),

--Apple iPhone 13 128Gb Red
(24, 6, 129, 108),
(24, 8, 130, 16),
(24, 25, 130, 85),
(24, 26, 130, 13),

--Apple iPhone 13 128Gb Black
(25, 6, 129, 108),
(25, 8, 85, 16),
(25, 24, 85, 130),
(25, 26, 85, 13),

--Apple iPhone 13 128Gb Pink
(26, 6, 129, 108),
(26, 8, 13, 16),
(26, 24, 13, 130),
(26, 25, 13, 85),

--Apple iPhone 15 256Gb Blue
(21, 4, 108, 106),
(21, 1, 108, 107),
(21, 6, 2, 1),

--Apple iPhone 15 Pro 256Gb Natural Titanium
(22, 1, 106, 107),
(22, 4, 8, 9),
(22, 23, 2, 3),

--Apple iPhone 15 Pro 512Gb Natural Titanium
(23, 4, 3, 2);

package io.teamchallenge.controller;

import io.teamchallenge.annotation.AllowedSortFields;
import io.teamchallenge.annotation.ImageValidation;
import io.teamchallenge.dto.filter.CameraFilter;
import io.teamchallenge.dto.filter.PriceFilter;
import io.teamchallenge.dto.filter.ProductFilterDto;
import io.teamchallenge.dto.pageable.AdvancedPageableDto;
import io.teamchallenge.dto.pageable.PageableDto;
import io.teamchallenge.dto.product.ProductRequestDto;
import io.teamchallenge.dto.product.ProductResponseDto;
import io.teamchallenge.dto.product.ShortProductResponseDto;
import io.teamchallenge.entity.Product;
import io.teamchallenge.enumerated.Color;
import io.teamchallenge.service.impl.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

/**
 * Controller for products.
 * @author Niktia Malov
 */
@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
@Validated
public class ProductController {
    private final ProductService productService;

    /**
     * Retrieves a paginated list of products based on the provided filter criteria and pagination settings.
     *
     * @param name              The name of the product to filter by.
     * @param brandIds          List of brand IDs to filter by.
     * @param categoryId        The category ID to filter by.
     * @param attributeValueIds List of attribute value IDs to filter by.
     * @param minPrice          The minimum price to filter by.
     * @param maxPrice          The maximum price to filter by.
     * @param pageable          Pageable object for pagination and sorting information.
     *                          Parameters: page, size, sort.
     *                          Allowed sort fields: "price", "popularity", "rating".
     *                          Default sort: "price" in descending order.
     * @return ResponseEntity containing a pageable list of short product responses.
     */
    @GetMapping
    public ResponseEntity<AdvancedPageableDto<ShortProductResponseDto>> getAll(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) List<Long> brandIds,
        @RequestParam(required = false) Long categoryId,
        @RequestParam(required = false) List<Long> attributeValueIds,
        @RequestParam(required = false) String minPrice,
        @RequestParam(required = false) String maxPrice,
        @RequestParam(required = false) String minMP,
        @RequestParam(required = false) String maxMP,
        @RequestParam(required = false) List<Color> colors,
        @AllowedSortFields(values = {"price","popularity","rating"})
        @PageableDefault(sort = "price", direction = DESC) Pageable pageable) {
        ProductFilterDto productFilterDto = ProductFilterDto.builder()
            .name(name)
            .price(PriceFilter.builder()
                    .from(minPrice == null ? 0 : Integer.parseInt(minPrice))
                    .to(maxPrice == null ? 1000000 : Integer.parseInt(maxPrice))
                    .build())
            .brandIds(brandIds)
            .categoryId(categoryId)
            .attributeValueIds(attributeValueIds)
                .cameraFilter(CameraFilter.builder()
                        .from(minMP == null ? null : Integer.parseInt(minMP))
                        .to(maxMP == null ? null : Integer.parseInt(maxMP))
                        .build())
            .colors(colors)
            .build();
        return ResponseEntity.ok(productService.getAll(pageable, productFilterDto));
    }


    /**
     * Retrieves a product by its unique identifier.
     *
     * @param id The identifier of the product to retrieve.
     * @return ResponseEntity containing the ProductResponseDto representing the retrieved product, with status OK.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    /**
     * Creates a new product with the provided product details and images.
     *
     * @param images            List of image files to be associated with the product.
     *                          Must pass custom @ImageValidation.
     * @param productRequestDto DTO containing product details for creation.
     *                          Must be valid as per validation constraints.
     * @return ResponseEntity containing the created product details with a status of CREATED (201).
     */
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProductResponseDto> create(
        @RequestPart @ImageValidation List<MultipartFile> images,
        @RequestPart @Valid ProductRequestDto productRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(productService.create(productRequestDto, images));
    }

    /**
     * Updates an existing product with the provided product details and optional images.
     *
     * @param id                The ID of the product to be updated.
     * @param images            Optional list of image files to be associated with the product.
     *                          Must pass custom @ImageValidation if provided.
     * @param productRequestDto DTO containing updated product details.
     *                          Must be valid as per validation constraints.
     * @return ResponseEntity containing the updated product details with a status of OK (200).
     */
    @PutMapping(path = "/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProductResponseDto> update(@PathVariable Long id,
                                                     @RequestPart(required = false) @ImageValidation
                                                     List<MultipartFile> images,
                                                     @RequestPart @Valid ProductRequestDto productRequestDto) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(productService.update(id, productRequestDto, images));
    }

    /**
     * Deletes a product by its unique identifier.
     *
     * @param id The identifier of the product to delete.
     * @return ResponseEntity with status NO_CONTENT.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/suggestions")
    public ResponseEntity<List<String>> getSuggestions(@RequestParam String query) {
        return ResponseEntity.ok(productService.getSuggestions(query));
    }

    @GetMapping("/search")
    public ResponseEntity<PageableDto<ShortProductResponseDto>> getSearchResults(@RequestParam String query,
                                                                                 @PageableDefault(sort = "price", direction = DESC) Pageable pageable) {
        return ResponseEntity.ok(productService.getSearchResults(query, pageable));
    }
}

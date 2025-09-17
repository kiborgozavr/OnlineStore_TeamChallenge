package io.teamchallenge.dto.filter;

import io.teamchallenge.enumerated.Color;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductFilterDto {
    private String name;
    private PriceFilter price;
    @Size(min = 1)
    private List<Long> brandIds;
    @Min(value = 1)
    private Long categoryId;
    @Size(min = 1)
    private List<Long> attributeValueIds;
    private CameraFilter cameraFilter;
    private List<Color> colors;
}

package io.teamchallenge.dto.address;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class AddressDto {

    @NotBlank
    private String city;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String street;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String houseNumber;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String flat;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String departmentNumber;
    private String addressLine;

}

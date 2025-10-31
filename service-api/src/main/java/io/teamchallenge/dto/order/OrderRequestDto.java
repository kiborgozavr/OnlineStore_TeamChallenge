package io.teamchallenge.dto.order;

import io.teamchallenge.dto.address.AddressDto;
import io.teamchallenge.dto.cart.CartItemRequestDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequestDto {
    @NotBlank
    private String fullName;

    @Email(message = "Please, insert valid email address")
    private String email;

    @NotBlank
    private String phoneNumber;

    private String comment;

    @Size(min = 1, message = "You need minimum one product")
    private List<CartItemRequestDto> cartItems;

    @NotNull
    private String paymentMethod;

    @NotNull
    private String deliveryMethod;

    @NotNull
    private AddressDto address;
}

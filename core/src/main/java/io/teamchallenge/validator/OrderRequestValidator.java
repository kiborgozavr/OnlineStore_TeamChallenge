package io.teamchallenge.validator;

import io.teamchallenge.annotation.ValidOrderRequest;
import io.teamchallenge.dto.order.OrderRequestDto;
import io.teamchallenge.enumerated.DeliveryMethod;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OrderRequestValidator implements ConstraintValidator<ValidOrderRequest, OrderRequestDto> {
    /**
     * Validates the delivery method and addresses in an {@link OrderRequestDto}.
     *
     * @param dto   the {@code OrderRequestDto} object to validate.
     * @param context the context in which the constraint is evaluated.
     * @return {@code true} if the delivery method is valid, {@code false} otherwise.
     */
    @Override
    public boolean isValid(OrderRequestDto dto, ConstraintValidatorContext context) {

        DeliveryMethod deliveryMethod = DeliveryMethod.mapDeliveryMethodByTitle(dto.getDeliveryMethod());
        return  (deliveryMethod.equals(DeliveryMethod.COURIER) && isAddressForCourierValid(dto))
                || ((deliveryMethod.equals(DeliveryMethod.NOVA) && isAddressForPostValid(dto))
                || deliveryMethod.equals(DeliveryMethod.UKRPOSHTA)) && isAddressForPostValid(dto);
    }

    private boolean isAddressForCourierValid(OrderRequestDto value) {
        return  value.getAddress() != null &&
                value.getAddress().getCity() != null && !value.getAddress().getCity().isBlank() &&
                value.getAddress().getStreet() != null && !value.getAddress().getStreet().isBlank() &&
                value.getAddress().getHouseNumber() != null && !value.getAddress().getHouseNumber().isBlank();
    }

    private boolean isAddressForPostValid(OrderRequestDto value) {
        return  value.getAddress() != null &&
                value.getAddress().getCity() != null && !value.getAddress().getCity().isBlank() &&
                value.getAddress().getDepartmentNumber() != null && !value.getAddress().getDepartmentNumber().isBlank();
    }
}

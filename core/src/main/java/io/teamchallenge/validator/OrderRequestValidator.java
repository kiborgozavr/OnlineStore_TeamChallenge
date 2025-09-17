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
     * @param value   the {@code OrderRequestDto} object to validate.
     * @param context the context in which the constraint is evaluated.
     * @return {@code true} if the delivery method is valid, {@code false} otherwise.
     */
    @Override
    public boolean isValid(OrderRequestDto value, ConstraintValidatorContext context) {
        return  (value.getDeliveryMethod().equals(DeliveryMethod.COURIER) && isAddressForCourierValid(value))
                || ((value.getDeliveryMethod().equals(DeliveryMethod.NOVA)
                || value.getDeliveryMethod().equals(DeliveryMethod.UKRPOSHTA)) && isAddressForPostValid(value));
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

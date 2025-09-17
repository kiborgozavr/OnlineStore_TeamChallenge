package io.teamchallenge.validator;


import io.teamchallenge.annotation.ValidProductReview;
import io.teamchallenge.dto.review.AddReviewRequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ProductReviewValidator implements ConstraintValidator<ValidProductReview, AddReviewRequestDto> {

    @Override
    public boolean isValid(AddReviewRequestDto addReviewRequestDto, ConstraintValidatorContext constraintValidatorContext) {
        return isRateValid(addReviewRequestDto.getRate()) && isTextValid(addReviewRequestDto.getText());
    }

    private boolean isTextValid(String text) {
        return text == null || text.length() <= 500;
    }

    private boolean isRateValid(short rate) {
        return rate <= 5 && rate>= 1;
    }
}

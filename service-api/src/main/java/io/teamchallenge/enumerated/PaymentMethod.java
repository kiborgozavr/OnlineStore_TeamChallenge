package io.teamchallenge.enumerated;

import lombok.Getter;

@Getter
public enum PaymentMethod {
    COURIER("To courier"),
    AFTER_CHECKIN("Payment after checking");


    private String title;

    PaymentMethod(String title) {
        this.title = title;
    }
}

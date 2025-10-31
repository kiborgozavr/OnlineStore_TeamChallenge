package io.teamchallenge.enumerated;

import lombok.Getter;

@Getter
public enum PaymentMethod {
    COURIER("To courier"),
    AFTER_CHECKING("Payment after checking");


    private String title;

    PaymentMethod(String title) {
        this.title = title;
    }

    public static PaymentMethod mapPaymentMethodByTitle(String title) { //todo: not public?
        if (title == null) return null;
        String normalized = title.trim();

        for (PaymentMethod p : values()) {
            if (p.name().equalsIgnoreCase(normalized) ||
                    p.getTitle().equalsIgnoreCase(normalized)) {
                return p;
            }
        }
        return null;
    }
}
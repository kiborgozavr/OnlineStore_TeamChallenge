package io.teamchallenge.enumerated;

import lombok.Getter;

@Getter
public enum DeliveryMethod {
    COURIER("By courier"),
    NOVA("Nova Poshta"),
    UKRPOSHTA("UkrPoshta");

    private String title;


    DeliveryMethod(String title) {
        this.title = title;
    }

    public static DeliveryMethod mapDeliveryMethodByTitle(String title) { //todo: not public?
        if (title == null) return null;
        String normalized = title.trim();

        for (DeliveryMethod d : values()) {
            if (d.name().equalsIgnoreCase(normalized) ||
                    d.getTitle().equalsIgnoreCase(normalized)) {
                return d;
            }
        }
        return null;
    }
}

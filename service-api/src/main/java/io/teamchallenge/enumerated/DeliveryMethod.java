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
}

package io.teamchallenge.enumerated;

public enum DeliveryMethod {
    COURIER("By courier"),
    NOVA("Nova Poshta"),
    UKRPOSHTA("UkrPoshta");

    private String title;


    DeliveryMethod(String title) {
        this.title = title;
    }
}

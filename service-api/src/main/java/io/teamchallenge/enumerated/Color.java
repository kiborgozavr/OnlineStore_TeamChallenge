package io.teamchallenge.enumerated;

public enum Color {
    WHITE("FFFFFF"),
    BLACK("000000"),
    RED("FF0000"),
    GREEN("008000"),
    BLUE("0000FF"),
    VIOLET("800080"),
    GREY("808080"),
    GOLD("FFD700"),
    ORANGE("FFA500"),
    PINK("FFC0CB");

    private final String hex;

    Color(String hex) {
        this.hex = hex;
    }

    public String getHex() {
        return this.hex;
    }
}

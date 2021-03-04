package ru.koryakin.dacha2.domain;

public enum MenuCategory {
    DESSERTS("ДЕСЕРТЫ", true),
    STARTERS("ЗАКУСКИ", true),
    GRILL("ГРИЛЬ", true),
    FISH("Рыба и морепродукты", true),
    SALADS("САЛАТЫ", false),
    SOUPS("СУПЫ", false),
    MEAT("Мясо и птица", true ),
    PASTE("ПАСТА", false),
    SIDE("ГАРНИРЫ", false),
    COFFEE("Кофе", false);

    private final String text;
    private final boolean isShow;

    MenuCategory(final String text, final boolean isShow) {
        this.text = text;
        this.isShow = isShow;
    }

    @Override
    public String toString() {
        return text;
    }

    public boolean isShow() {
        return isShow;
    }
}

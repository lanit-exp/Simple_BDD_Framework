package ru.lanit.at.utils;

import java.util.Arrays;

/**
 * Адреса стендов
 */
public enum Stand {
    GOOGLE("www.google.ru"),
    YANDEX("ya.ru");

    private String url;

    Stand(String url) {
        this.url = url;
    }

    public static Stand getByName(String value) {
        return Arrays.stream(values())
                .filter(stand -> stand.name().equalsIgnoreCase(value)).findFirst().orElseThrow(() -> new RuntimeException("Не определен подходящий стенд с именем " + value));
    }

    public String getUrlPath() {
        return url;
    }

}

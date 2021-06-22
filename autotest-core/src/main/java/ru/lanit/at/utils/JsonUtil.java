package ru.lanit.at.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.InvalidJsonException;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import io.qameta.allure.Allure;

import java.nio.charset.StandardCharsets;

public class JsonUtil {


    /**
     * парсит к utf-8
     *
     * @param text текст
     * @return текст в utf-8 (насколько это возможно)
     */
    public static String jsonToUtf(String text) {
        return new String(text.getBytes(), StandardCharsets.UTF_8);
    }

    /**
     * извлекает данные по json path из json
     *
     * @param body     json
     * @param jsonPath json path
     * @return значение из json
     */
    public static String getFieldFromJson(String body, String jsonPath) {
        Configuration jacksonConfig = Configuration.builder()
                .mappingProvider(new JacksonMappingProvider())
                .jsonProvider(new JacksonJsonProvider())
                .build();

        String val;
        JsonNode node;
        try {
            node = JsonPath.using(jacksonConfig).parse(body).read(jsonPath, JsonNode.class);
        } catch (InvalidJsonException e) {
            Allure.addAttachment("INVALID JSON", "application/json", body, ".txt");
            throw new InvalidJsonException("Невалидный json.");
        }

        if (node == null || node.isNull()) {
            val = "null";
        } else {
            val = node.toString();
        }

        String matchValue = RegexUtil.getMatchValueByGroupNumber(val, "^\"(.*)\"$", 1);
        val = matchValue == null ? val : matchValue;
        return val;
    }
}

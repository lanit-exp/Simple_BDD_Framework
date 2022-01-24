package ru.lanit.at.steps;


import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.lanit.at.utils.ContextHolder;
import ru.lanit.at.utils.DataGenerator;

import java.util.Map;

import static ru.lanit.at.utils.ContextHolder.replaceVarsIfPresent;

public class VariableSteps {
    private Logger LOGGER = LogManager.getLogger(this.getClass());

    /**
     * Сгенерировать и Сохранить значение
     *
     * @param field - наименование элемента
     * @param key   - значение
     */
    @Когда("сгенерировать значение {string} и сохранить под именем {string}")
    public void generateAndSaveValue(String field, String key) {
        String value = DataGenerator.generateValueByMask(field);
        ContextHolder.asMap().put(key, value);
        LOGGER.info("значение '{}' сохранено под именем '{}'", value, key);
    }

    @И("сгенерировать переменные")
    public void generateVariables(Map<String, String> table) {
        table.forEach((k, v) -> {
            String value = DataGenerator.generateValueByMask(replaceVarsIfPresent(v));
            ContextHolder.put(k, value);
            Allure.addAttachment(k, "application/json", k + ": " + value, ".txt");
            LOGGER.info("Сгенерирована переменная: {}={}", k, value);
        });
    }

    @И("создать контекстные переменные")
    public void createContextVariables(Map<String, String> table) {
        table.forEach((k, v) -> {
            ContextHolder.put(k, v);
            LOGGER.info("Сохранена переменная: {}={}", k, v);
        });
    }


}

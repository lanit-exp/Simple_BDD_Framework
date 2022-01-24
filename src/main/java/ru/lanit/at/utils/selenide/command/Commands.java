package ru.lanit.at.utils.selenide.command;

import com.codeborne.selenide.Command;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementShould;
import com.codeborne.selenide.ex.ElementShouldNot;
import org.aeonbits.owner.ConfigFactory;
import ru.lanit.at.assertion.AssertsManager;
import ru.lanit.at.utils.web.properties.Configurations;

import java.time.Duration;

/**
 * Кастомные команды selenide элемента
 */
public class Commands {

    public static final Configurations conf = ConfigFactory.create(Configurations.class, System.getProperties(),
            System.getenv());

    private Commands() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Мягкая проверка Conditions element
     *
     * @param condition - тип проверки
     * @param duration  -   таймаут ожидания (может быть null, в таком случае будет использоваться параметр из конфига webdriver.timeoutSeconds )
     * @return - proxy элемент
     */

    public static Command<SelenideElement> checkSoft(Condition condition, Duration duration) {
        return (proxy, locator, args) -> {
            try {
                return proxy.should(condition, duration);
            } catch (ElementShould | ElementShouldNot ex) {
                AssertsManager.getAssertsManager().softAssert().fail(ex.getMessage(), ex);
            }
            return proxy;
        };
    }

}

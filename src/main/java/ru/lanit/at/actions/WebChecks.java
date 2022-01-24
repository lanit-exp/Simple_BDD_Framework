package ru.lanit.at.actions;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.ex.ElementNotFound;
import com.codeborne.selenide.ex.ElementShould;
import org.aeonbits.owner.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import ru.lanit.at.utils.ErrorMessage;
import ru.lanit.at.utils.selenide.command.Commands;
import ru.lanit.at.utils.web.properties.WebConfigurations;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.format;

public class WebChecks {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebChecks.class);


    private static Integer getTimeoutSeconds(Integer timeout) {
        WebConfigurations cfg = ConfigFactory.create(WebConfigurations.class);
        return timeout == null ? cfg.webDriverTimeoutSeconds() : timeout;
    }

    /**
     * Проверяет что текущий url равен переданному
     *
     * @param url ожидаемый url
     */
    public static void urlEquals(String url) {
        Assert.assertEquals(WebDriverRunner.getWebDriver().getCurrentUrl(), url,
                format(ErrorMessage.URL_NOT_EQUAL_ACTUAL, url, WebDriverRunner.getWebDriver().getCurrentUrl()));
        LOGGER.info("url '{}' равен текущему '{}'", url, WebDriverRunner.getWebDriver().getCurrentUrl());
    }

    /**
     * Проверяет что текущий url содержит текст
     *
     * @param text ожидаемый текст
     */
    public static void urlContains(String text) {
        Assert.assertTrue(WebDriverRunner.getWebDriver().getCurrentUrl().contains(text),
                format(ErrorMessage.URL_NOT_CONTAINS_TEXT, WebDriverRunner.getWebDriver().getCurrentUrl(), text));
        LOGGER.info("Текущий url '{}' содержит текст '{}'", WebDriverRunner.getWebDriver().getCurrentUrl(), text);
    }

    /**
     * Проверяет, что атрибут элемента равен ожидаемому
     *
     * @param element     Элемент
     * @param attrName    Атрибут
     * @param expectValue Ожидаемое значение атрибута
     */
    public static void checkAttribute(SelenideElement element, String attrName, String expectValue, Integer timeoutSeconds) {
        int timeout = getTimeoutSeconds(timeoutSeconds);
        element
                .shouldBe(Condition.exist, Duration.ofSeconds(timeout))
                .shouldBe(Condition.attribute(attrName, expectValue), Duration.ofSeconds(timeout));
    }

    /**
     * Проверяет, что на странице доступен элемент
     */
    public static void elementEnablesOnPage(SelenideElement element, Integer timeoutSeconds) {
        int timeout = getTimeoutSeconds(timeoutSeconds);
        element.shouldBe(Condition.enabled, Duration.ofSeconds(timeout));
    }


    /**
     * Проверяет, что на странице имеется элемент
     */
    public static void elementVisibleOnPage(SelenideElement element, Integer timeoutSeconds) {
        int timeout = getTimeoutSeconds(timeoutSeconds);
        element.shouldBe(Condition.visible, Duration.ofSeconds(timeout));
    }


    /**
     * Проверяет, что элемент имеет текст
     */
    public static void elementContainsText(SelenideElement element, String text) {
        element.execute(Commands.checkSoft(Condition.text(text), null));
    }

    /**
     * Проверяет, что элемент содержит текст
     */
    public static void elementContainsText(SelenideElement element, List<String> texts) {
        for (String s : texts) {
            element.execute(Commands.checkSoft(Condition.text(s), Duration.ofSeconds(10)));
        }
    }


    /**
     * Проверяет, что на странице имеется текст
     */
    public static void textVisibleOnPage(String text, Integer timeoutSeconds) {
        int timeout = getTimeoutSeconds(timeoutSeconds);
        $(Selectors.byText(text))
                .shouldBe(Condition.visible, Duration.ofSeconds(timeout));
    }

    /**
     * Проверяет, что на странице отсутствует текст
     */
    public static void textAbsentOnPage(String text, Integer timeoutSeconds) {
        int timeout = getTimeoutSeconds(timeoutSeconds);
        $(Selectors.byText(text))
                .shouldBe(Condition.not(Condition.visible), Duration.ofSeconds(timeout));
    }

    /**
     * Проверяет, что на странице отсутствует элемент
     */
    public static void elementAbsentOnPage(SelenideElement element, Integer timeoutSeconds) {
        int timeout = getTimeoutSeconds(timeoutSeconds);
        element
                .shouldBe(Condition.not(Condition.visible), Duration.ofSeconds(timeout));
    }

    /**
     * Проверяет, что текст элемента соответствует ожидаемому тексту
     *
     * @param element        элемент
     * @param expectedText   ожидаемый текст
     * @param timeoutSeconds количество секунд, в течении этого времени ожидается текст
     */
    public static void elementTextEqualsExpectedText(SelenideElement element, String expectedText, Integer timeoutSeconds) {
        int timeout = getTimeoutSeconds(timeoutSeconds);
        element.shouldBe(Condition.exactTextCaseSensitive(expectedText), Duration.ofSeconds(timeout));
    }

    /**
     * проверяет что текст элемента не соответствует ожидаемому тексту
     *
     * @param element        элемент
     * @param expectedText   текст
     * @param timeoutSeconds количество секунд
     */
    public static void elementTextNotEqualsExpectedText(SelenideElement element, String expectedText, Integer timeoutSeconds) {
        int timeout = getTimeoutSeconds(timeoutSeconds);
        element.shouldNotBe(Condition.exactTextCaseSensitive(expectedText), Duration.ofSeconds(timeout));
    }

    /**
     * Проверяет, что текст элемента содержит ожидаемый текст
     *
     * @param element        элемент
     * @param expectedText   ожидаемый текст или регулярное выражение
     * @param timeoutSeconds количество секунд, в течении этого времени ожидается текст
     */
    public static void elementTextContainsExpectedText(SelenideElement element, String expectedText, Integer timeoutSeconds) {
        int timeout = getTimeoutSeconds(timeoutSeconds);
        element.shouldBe(Condition.matchText(expectedText), Duration.ofSeconds(timeout));
    }

    /**
     * Проверяет, появился ли элемент за переданное время
     *
     * @param element        - веб-элемент
     * @param timeoutSeconds -   таймаут ожидания (может быть null, в таком случае будет использоваться параметр из конфига webdriver.timeoutSeconds )
     * @return -   появился ли элемент
     */
    public static boolean isElementWillAppear(SelenideElement element, Integer timeoutSeconds) {
        try {
            elementVisibleOnPage(element, timeoutSeconds);
            return true;
        } catch (ElementNotFound elementNotFound) {
            return false;
        }
    }

    /**
     * Проверяет, исчез ли элемент за переданное время
     *
     * @param element        веб-элемент
     * @param timeoutSeconds -   таймаут ожидания (может быть null, в таком случае будет использоваться параметр из конфига webdriver.timeoutSeconds )
     * @return -   исчез ли элемент
     */
    public static boolean isElementWillDisappear(SelenideElement element, Integer timeoutSeconds) {
        try {
            elementAbsentOnPage(element, timeoutSeconds);
            return true;
        } catch (ElementShould elementShould) {
            return false;
        }
    }

    /**
     * Проверяет, исчез ли элемент за переданное время
     *
     * @param xpath          - xpath веб-элемента
     * @param timeoutSeconds -   таймаут ожидания (может быть null, в таком случае будет использоваться параметр из конфига webdriver.timeoutSeconds )
     * @return -   исчез ли элемент
     */
    public static boolean isElementWillDisappear(String xpath, Integer timeoutSeconds) {
        try {
            SelenideElement element = $(Selectors.byXpath(xpath));
            elementAbsentOnPage(element, timeoutSeconds);
            return true;
        } catch (ElementShould elementShould) {
            return false;
        }
    }
}

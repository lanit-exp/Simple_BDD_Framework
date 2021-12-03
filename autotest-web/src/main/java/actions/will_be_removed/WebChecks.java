package actions.will_be_removed;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import com.codeborne.selenide.ex.ElementShould;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import ru.lanit.at.utils.ErrorMessage;
import ru.lanit.at.web.pagecontext.Environment;
import ru.lanit.at.web.properties.WebConfigurations;

import java.time.Duration;

import static com.codeborne.selenide.Condition.href;
import static com.codeborne.selenide.Selenide.$;

@Deprecated
public class WebChecks {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebChecks.class);
    private static final WebDriver DRIVER = Environment.getDriver();

    private static Integer getTimeoutSecondsFirst(Integer timeoutt) {
        WebConfigurations cfg = ConfigFactory.create(WebConfigurations.class,
                System.getProperties(),
                System.getenv());
        return timeoutt == null ? cfg.webDriverTimeoutSeconds() : timeoutt;
    }

    /**
     * Проверяет что текущий url равен переданному
     *
     * @param url ожидаемый url
     */
    public static void urlEquals(String url) {
        Assert.assertEquals(DRIVER.getCurrentUrl(), url,
                String.format(ErrorMessage.URL_NOT_EQUAL_ACTUAL, url, DRIVER.getCurrentUrl()));
        LOGGER.info("url '{}' равен текущему '{}'", url, DRIVER.getCurrentUrl());
    }

    /**
     * Проверяет что текущий url содержит текст
     *
     * @param text ожидаемый текст
     */
    public static void urlContains(String text) {
        Assert.assertTrue(DRIVER.getCurrentUrl().contains(text),
                String.format(ErrorMessage.URL_NOT_CONTAINS_TEXT, DRIVER.getCurrentUrl(), text));
        LOGGER.info("Текущий url '{}' содержит текст '{}'", DRIVER.getCurrentUrl(), text);
    }

    /**
     * Проверяет, что атрибут элемента равен ожидаемому
     *
     * @param element     Элемент
     * @param attrName    Атрибут
     * @param expectValue Ожидаемое значение атрибута
     */
    public static void checkAttribute(SelenideElement element, String attrName, String expectValue, Integer timeoutSeconds) {
        int timeout = getTimeoutSecondsFirst(timeoutSeconds);
        element
                .shouldBe(Condition.exist, Duration.ofSeconds(timeout))
                .shouldBe(Condition.attribute(attrName, expectValue), Duration.ofSeconds(timeout));
    }

    /**
     * Проверяет, что на странице имеется элемент
     */
    public static void elementVisibleOnPage(SelenideElement element, Integer timeoutSeconds) {
        int timeout = getTimeoutSecondsFirst(timeoutSeconds);
        element.shouldBe(Condition.visible, Duration.ofSeconds(timeout));
    }

    /**
     * Проверяет, что на странице имеется текст
     */
    public static void textVisibleOnPage(String text, Integer timeoutSeconds) {
        int timeout = getTimeoutSecondsFirst(timeoutSeconds);
        $(Selectors.byText(text))
                .shouldBe(Condition.visible, Duration.ofSeconds(timeout));
    }

    /**
     * Проверяет, что на странице отсутствует текст
     */
    public static void textAbsentOnPage(String text, Integer timeoutSeconds) {
        int timeout = getTimeoutSecondsFirst(timeoutSeconds);
        $(Selectors.byText(text))
                .shouldBe(Condition.not(Condition.visible), Duration.ofSeconds(timeout));
    }

    /**
     * Проверяет, что на странице отсутствует элемент
     */
    public static void elementAbsentOnPage(SelenideElement element, Integer timeoutSeconds) {
        int timeout = getTimeoutSecondsFirst(timeoutSeconds);
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
        int timeout = getTimeoutSecondsFirst(timeoutSeconds);
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
        int timeout = getTimeoutSecondsFirst(timeoutSeconds);
        element.shouldNotBe(Condition.exactTextCaseSensitive(expectedText), Duration.ofSeconds(timeout));
    }

    /**
     * проверяет что значение элемента соответствует ожидаемому тексту
     *
     * @param element        элемент
     * @param expectedText   текст
     * @param timeoutSeconds количество секунд
     */
    public static void elementValueEqualsExpectedText(SelenideElement element, String expectedText, Integer timeoutSeconds) {
        int timeout = getTimeoutSecondsFirst(timeoutSeconds);
        element.shouldBe(Condition.value(expectedText), Duration.ofSeconds(timeout));
    }

    /**
     * проверяет что элемент заблокирован
     *
     * @param element        элемент
     * @param timeoutSeconds количество секунд
     */
    public static void elementIsReadOnly(SelenideElement element, String text, Integer timeoutSeconds) {
        int timeout = getTimeoutSecondsFirst(timeoutSeconds);
        element.shouldBe(Condition.cssClass(text), Duration.ofSeconds(timeout));
    }

    /**
     * Проверяет, что текст элемента содержит ожидаемый текст
     *
     * @param element        элемент
     * @param expectedText   ожидаемый текст или регулярное выражение
     * @param timeoutSeconds количество секунд, в течении этого времени ожидается текст
     */
    public static void elementTextContainsExpectedText(SelenideElement element, String expectedText, Integer timeoutSeconds) {
        int timeout = getTimeoutSecondsFirst(timeoutSeconds);
        element.shouldBe(Condition.matchText(expectedText), Duration.ofSeconds(timeout));
    }

    /**
     * Проверяет, появился ли элемент за переданное время
     *
     * @param element        - веб-элемент
     * @param timeoutSeconds -   таймаут ожидания (может быть null)
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
     * @param timeoutSeconds -   таймаут ожидания (может быть null)
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
     * @param timeoutSeconds -   таймаут ожидания (может быть null)
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

    /**
     * Проверяет, что на странице не активен элемент
     */
    public static void elementIsNotOnPage(SelenideElement element, String text) {
        element.shouldNotHave(Condition.href(text));
    }

    /**
     * Проверяет, что на странице активен элемент
     */
    public static void elementIsOn(SelenideElement element) {
        element.isEnabled();
    }

    public static void elementVisibleAndNoEnable(SelenideElement element) {
        element.shouldBe(Condition.visible);
        element.shouldNotBe(Condition.enabled);
    }

    private static Integer getTimeoutSeconds(Integer timeout) {
        WebConfigurations cfg = ConfigFactory.create(WebConfigurations.class,
                System.getProperties(),
                System.getenv());
        return timeout == null ? cfg.webDriverTimeoutSeconds() : timeout;
    }
}

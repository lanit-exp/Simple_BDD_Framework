package actions;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import org.aeonbits.owner.ConfigFactory;
import ru.lanit.at.web.properties.WebConfigurations;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class Checks {

    private static Integer getTimeoutSecondsFirst(Integer timeoutt) {
        WebConfigurations cfg = ConfigFactory.create(WebConfigurations.class,
                System.getProperties(),
                System.getenv());
        return timeoutt == null ? cfg.webDriverTimeoutSeconds() : timeoutt;
    }

    public static void elementNotVisible(SelenideElement element) {
        element.shouldHave(Condition.exactText(""));
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
     * Проверяет, что на странице не отображается элемент
     */
    public static void elementNotVisibleOnPage(SelenideElement element, Integer timeoutSeconds) {
        int timeout = getTimeoutSecondsFirst(timeoutSeconds);
        element.shouldBe(Condition.not(Condition.visible), Duration.ofSeconds(timeout));
    }

    /**
     * Проверяет, что на странице имеется текст
     */
    public static void textVisibleOnPage(String text, Integer timeoutSeconds) {
        int timeout = getTimeoutSecondsFirst(timeoutSeconds);
        $(Selectors.byText(text)).shouldBe(Condition.visible, Duration.ofSeconds(timeout));
    }

    /**
     * Проверяет, что на странице отсутствует текст
     */
    public static void textAbsentOnPage(String text, Integer timeoutSeconds) {
        int timeout = getTimeoutSecondsFirst(timeoutSeconds);
        $(Selectors.byText(text)).shouldBe(Condition.not(Condition.visible), Duration.ofSeconds(timeout));
    }

    /**
     * Проверяет, что на странице отсутствует элемент
     */
    public static void elementAbsentOnPage(SelenideElement element, Integer timeoutSeconds) {
        int timeout = getTimeoutSecondsFirst(timeoutSeconds);
        element.shouldBe(Condition.not(Condition.visible), Duration.ofSeconds(timeout));
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
     * Проверяет, что на странице не активен элемент
     */
    public static void elementIsNotOnPage(SelenideElement element, String text) {
        element.shouldNotHave(Condition.href(text));
    }

    /**
     * Проверяет, что на странице активен элемент
     */
    public static void elementIsOnPage(SelenideElement element, String text) {
        element.shouldNotHave(Condition.href(text));
    }

    public static void elementVisibleAndNoSelected(SelenideElement element) {
        element.shouldBe(Condition.visible);
        element.shouldNotBe(Condition.selected);
    }

    public static void elementVisibleAndSelected(SelenideElement element) {
        element.shouldBe(Condition.visible);
        element.shouldBe(Condition.selected);
    }

    public static void elementTextEqualsExpectedText(SelenideElement element, String expectedText) {
        element.shouldBe(Condition.exactTextCaseSensitive(expectedText));
    }

    private static Integer getTimeoutSeconds(Integer timeout) {
        WebConfigurations cfg = ConfigFactory.create(WebConfigurations.class,
                System.getProperties(),
                System.getenv());
        return timeout == null ? cfg.webDriverTimeoutSeconds() : timeout;
    }

    public static void fieldVisibleAndNoSelected(SelenideElement element) {
        element.shouldBe(Condition.visible);
        element.shouldBe(Condition.empty);
    }

    public static void elementIsOn(SelenideElement element) {
        element.isEnabled();
    }

    public static void emptyElement(SelenideElement element) {
        element.shouldHave(Condition.empty);
    }
}

package actions;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.aeonbits.owner.ConfigFactory;
import ru.lanit.at.web.properties.WebConfigurations;

import java.time.Duration;

public class Checks {

    public static void elementVisible(SelenideElement element) {
        element.shouldBe(Condition.visible);
    }

    public static void elementVisibleAndNoSelected(SelenideElement element) {
        element.shouldBe(Condition.visible);
        element.shouldNotBe(Condition.selected);
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

    public static void elementVisibleOnPage(SelenideElement element, Integer timeoutSeconds) {
        int timeout = getTimeoutSeconds(timeoutSeconds);
        element.shouldBe(Condition.visible, Duration.ofSeconds(timeout));
    }
}

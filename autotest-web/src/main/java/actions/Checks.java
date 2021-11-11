package actions;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

public class Checks {

    public static void elementVisible(SelenideElement element) {
        element.shouldBe(Condition.visible);
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
}

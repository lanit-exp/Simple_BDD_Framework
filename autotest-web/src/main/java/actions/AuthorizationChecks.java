package actions;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.ru.Пусть;
import org.aeonbits.owner.ConfigFactory;
import ru.lanit.at.web.pagecontext.PageManager;
import ru.lanit.at.web.properties.WebConfigurations;

import java.time.Duration;

public class AuthorizationChecks {

    private PageManager pageManager;

    public AuthorizationChecks(PageManager pageManager) {
        this.pageManager = pageManager;
    }

    private static Integer getTimeoutSeconds(Integer timeout) {
        WebConfigurations cfg = ConfigFactory.create(WebConfigurations.class,
                System.getProperties(),
                System.getenv());
        return timeout == null ? cfg.webDriverTimeoutSeconds() : timeout;
    }

    @Пусть("поле ввода {string} отображается")
    public void checkAppearField(String elementName, Integer timeoutSeconds) {
        int timeout = getTimeoutSeconds(timeoutSeconds);
        SelenideElement element = pageManager.getCurrentPage().getElement(elementName);
        element.shouldBe(Condition.visible, Duration.ofSeconds(timeout));
    }

    @Пусть("чекбокс {string} отображается и не выбран")
    public void checkAppearCheckbox(String elementName, Integer timeoutSeconds) {
        int timeout = getTimeoutSeconds(timeoutSeconds);
        SelenideElement element = pageManager.getCurrentPage().getElement(elementName);
        element.shouldBe(Condition.visible, Duration.ofSeconds(timeout));
        element.shouldNotBe(Condition.selected);
    }

    @Пусть("кнопка {string} отображается")
    public void checkAppearButton(String elementName, Integer timeoutSeconds) {
        int timeout = getTimeoutSeconds(timeoutSeconds);
        SelenideElement element = pageManager.getCurrentPage().getElement(elementName);
        element.shouldBe(Condition.visible, Duration.ofSeconds(timeout));
    }

    public void checkErrorMessageText() {

    }

    public void checkPopUpErrorMessageText() {

    }
}

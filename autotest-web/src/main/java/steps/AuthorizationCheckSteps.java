package steps;

import actions.Checks;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.ru.Пусть;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.lanit.at.web.pagecontext.PageManager;

public class AuthorizationCheckSteps {

    private PageManager pageManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationCheckSteps.class);

    public AuthorizationCheckSteps(PageManager pageManager) {
        this.pageManager = pageManager;
    }

    @Пусть("чекбокс {string} отображается и не выбран")
    public void checkAppearCheckbox(String elementName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.elementVisibleAndNoSelected(element);
        LOGGER.info("на странице '{}' имеется элемент '{}'", pageManager.getCurrentPage().name(), elementName);
    }

    @Пусть("поле ввода {string} отображается")
    public void checkAppearField(String elementName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.elementVisible(element);
        LOGGER.info("на странице '{}' имеется элемент '{}'", pageManager.getCurrentPage().name(), elementName);
    }

    @Пусть("{string} отображается")
    public void checkAppearButton(String elementName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.elementVisible(element);
        LOGGER.info("на странице '{}' имеется элемент '{}'", pageManager.getCurrentPage().name(), elementName);
    }

//TODO
    public void checkErrorMessageText() {
    }

//TODO
    public void checkPopUpErrorMessageText() {
    }
}

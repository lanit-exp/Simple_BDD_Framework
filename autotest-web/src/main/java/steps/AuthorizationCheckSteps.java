package steps;

import actions.Checks;
import actions.WebChecks;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
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
    public void checkNoSelectedCheckbox(String elementName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.elementVisibleAndNoSelected(element);
        LOGGER.info("на странице '{}' имеется элемент '{}'", pageManager.getCurrentPage().name(), elementName);
    }
    @Пусть("чекбокс {string} отображается и выбран")
    public void checkSelectedCheckbox(String elementName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.elementVisibleAndSelected(element);
        LOGGER.info("на странице '{}' имеется элемент '{}'", pageManager.getCurrentPage().name(), elementName);
    }

     @Если("поле {string} отображается")
     @Если("кнопка {string} отображается")
     @Пусть("поле ввода {string} отображается")
     public void checkAppearElement(String elementName) {
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

    @Если("на странице отсутствует текст {string}")
    public void currentTextIsNotExist(String errorText) {
        WebChecks.textAbsentOnPage(errorText, null);
        LOGGER.info("на странице '{}' отсутствует текст '{}'", pageManager.getCurrentPage().name(), errorText);
    }

    @Если("на странице имеется элемент {string}")
    public void elementAppearsOnThePage(String elementName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.elementVisibleOnPage(element, null);
        LOGGER.info("на странице '{}' имеется элемент '{}'", pageManager.getCurrentPage().name(), elementName);
    }

    @Если("на странице в поле Добро пожаловать, имеется элемент {string}")
    public void elementIsHere(String elementName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.elementVisibleOnPage(element, null);
        LOGGER.info("на странице '{}' имеется элемент '{}'", pageManager.getCurrentPage().name(), elementName);
    }

    @Если("поле {string} содержит значение {string}")
    public void matchText(String elementName, String text) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.elementTextEqualsExpectedText(element, text);
        LOGGER.info("на странице '{}' имеется элемент '{}'", pageManager.getCurrentPage().name(), elementName);
    }

}

package steps;

import actions.Checks;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.И;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.lanit.at.web.pagecontext.PageManager;

public class EmployeeCheckSteps {

    private PageManager pageManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeCheckSteps.class);

    public EmployeeCheckSteps(PageManager pageManager) {
        this.pageManager = pageManager;
    }

    @Если("на текущей странице {string} с текстом {string} присутствует")
    @И("поле {string} инфоблок с текстом {string} присутствует")
    public void matchText(String elementName, String text) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.elementTextEqualsExpectedText(element, text);
        LOGGER.info("на странице '{}' имеется элемент '{}'", pageManager.getCurrentPage().name(), elementName);
    }
    @Если("в полях The Сотрудник “ и ” was changed successfully. You may edit it again below. присутствует элемент {string}")
    public void curFieldsContainsThatElement(String elementName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.elementVisibleOnPage(element, null);
    }

    @Если("в поле The Сотрудник “ и ” was changed successfully. присутствует элемент {string}")
    public void curFieldContainsThatElement(String elementName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.elementVisibleOnPage(element, null);
    }

    @Если("на странице в блоке {string} выбрать элемент {string}")
    public void elementIsOnPage(String elementN, String elementName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementN);
        ElementsCollection elements = pageManager
                .getCurrentPage()
                .getElementsCollection(elementName);
        elements.get(25).click();
        LOGGER.info("на текущей странице в блоке '{}' нажимается элемент '{}'", pageManager.getCurrentPage().name(), elementName);
    }
}

package steps;

import actions.Checks;
import actions.WebChecks;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
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
        LOGGER.info("в полях имеется элемент '{}'", elementName);
    }

    @Если("в поле The Сотрудник “ и ” was changed successfully. присутствует элемент {string}")
    public void curFieldContainsThatElement(String elementName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.elementVisibleOnPage(element, null);
        LOGGER.info("в поле имеется элемент '{}'", elementName);
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

    @Если("в таблице все элементы {string} содержат текст {string}")
    public void elementContainsText(String element, String text) {
        ElementsCollection elements = pageManager
                .getCurrentPage()
                .getElementsCollection(element);
        for (SelenideElement selenideElement : elements) {
            selenideElement.shouldBe(Condition.matchText(text));
        }
        LOGGER.info("на текущей странице в элементах '{}' присутствует текст '{}'", pageManager.getCurrentPage().name(), text);
    }

    @Если("в блоке {string} количество записей равно {int}")
    public void matchRecordsNumber(String elementName, int number) {
        ElementsCollection elements = pageManager
                .getCurrentPage()
                .getElementsCollection(elementName);
        Assert.assertEquals(elements.size(), number);
        LOGGER.info("на странице '{}' в блоке '{}' количество записей '{}'", pageManager.getCurrentPage().name(), elementName, number);
    }

    private String getElementText(String elementName, int index) {
        ElementsCollection elements = pageManager
                .getCurrentPage()
                .getElementsCollection(elementName);
        return elements.get(index).getText();
    }

    @Если("при нажатии на кнопку {string} в блоке 'Таблица' в столбце {string}, {int} элемент не изменился")
    public void matchFirstElement(String buttonName, String elementName, int index) {
        String firstResult = getElementText(elementName, index);
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(buttonName);
        element.click();
        String secondResult = getElementText(elementName, index);

        Assert.assertEquals(firstResult, secondResult);
        LOGGER.info("на странице '{}' в блоке '{}' запись '{}' осталась '{}'", pageManager.getCurrentPage().name(), elementName, firstResult, secondResult);
    }

    @Пусть("поле {string} отображается и пусто")
    public void checkEmptyField(String elementName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.fieldVisibleAndNoSelected(element);
        LOGGER.info("в блоке есть пустое поле '{}'", elementName);
    }
    }

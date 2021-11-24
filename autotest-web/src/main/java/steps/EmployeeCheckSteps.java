package steps;

import actions.Checks;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import ru.lanit.at.api.testcontext.ContextHolder;
import ru.lanit.at.web.pagecontext.PageManager;

import java.util.ArrayList;
import java.util.List;

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
        LOGGER.info("на странице '{}' имеется элемент '{}', с текстом '{}'", pageManager.getCurrentPage().name(), elementName, text);
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
        LOGGER.info("в поле имеется элемент '{}'", element.getText());
    }

    @Если("на странице в блоке {string} выбрать элемент {string}")
    public void elementIsOnPage(String elementN, String elementName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementN);
        SelenideElement elementTwo = pageManager
                .getCurrentPage()
                .getElement(elementName);
        elementTwo.click();
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

    @И("в текущем блоке {string} взять {int} элемент")
    public void getElementText(String elementName, int index) {
        ElementsCollection elements = pageManager
                .getCurrentPage()
                .getElementsCollection(elementName);
        ContextHolder.put("fio1", elements.get(index - 1).getText());
        LOGGER.info("на странице '{}' в блоке '{}' запись '{}", pageManager.getCurrentPage().name(), elementName, ContextHolder.getValue("fio1").toString());
    }

    @Если("при нажатии на кнопку {string} в блоке 'Таблица' в столбце {string}, {int} элемент не изменился")
    public void checkElementEquals(String buttonName, String elementName, int index) {
        SelenideElement button = pageManager
                .getCurrentPage()
                .getElement(buttonName);
        button.click();
        ElementsCollection elements = pageManager
                .getCurrentPage()
                .getElementsCollection(elementName);
        ContextHolder.put("fio2", elements.get(index - 1).getText());

        Assert.assertEquals(ContextHolder.getValue("fio1").toString(), ContextHolder.getValue("fio2").toString());
        LOGGER.info("на странице '{}' в блоке '{}' запись '{}' осталась '{}'", pageManager.getCurrentPage().name(), elementName, ContextHolder.getValue("fio1").toString(), ContextHolder.getValue("fio2").toString());
    }

    @Тогда("{int} запись в блоке {string} изменилась")
    public void checkElementNoEquals(int index, String elementName) {
        ElementsCollection elements = pageManager
                .getCurrentPage()
                .getElementsCollection(elementName);
        ContextHolder.put("fio2", elements.get(index - 1).getText());

        Assert.assertNotEquals(ContextHolder.getValue("fio1").toString(), ContextHolder.getValue("fio2").toString());
        LOGGER.info("на странице '{}' в блоке '{}' запись '{}' изменился на '{}'", pageManager.getCurrentPage().name(), elementName, ContextHolder.getValue("fio1").toString(), ContextHolder.getValue("fio2").toString());
    }

    @Если("в {string} активный номер {string}")
    public void checkCurrentNumber(String elementName, String number) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.elementTextEqualsExpectedText(element, number);
        LOGGER.info("на странице '{}' в блоке '{}' текущий номер '{}'", pageManager.getCurrentPage().name(), elementName, number);
    }

    @Если("в блоке 'Таблица' в столбце {string} все записи = {string}")
    public void checkCurrenNumberCity(String elementName, String city) {
        ElementsCollection elements = pageManager
                .getCurrentPage()
                .getElementsCollection(elementName);
        boolean result = true;
        for (SelenideElement element : elements) {
            if (!element.getText().equals(city)) {
                result = false;
                break;
            }
        }
        Assert.assertTrue(result);
        LOGGER.info("на странице '{}' в блоке '{}' текущий город '{}': '{}'", pageManager.getCurrentPage().name(), elementName, city, result);
    }

    @И("поле {string} присутствует на странице")
    @И("кнопка {string} присутствует на странице")
    public void checkElementVisibility(String elementName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.elementVisible(element);
        LOGGER.info("элемент '{}' отображается", elementName);
    }

    @И("на странице присутствует {string} {string}")
    public void checkElementVisibleCollection(String elementName, String text) {
        ElementsCollection elements = pageManager
                .getCurrentPage()
                .getElementsCollection(elementName);
        boolean result = false;
        for (SelenideElement element : elements) {
            if (element.getText().equals(text)) {
                result = true;
                break;
            }
        }
        Assert.assertTrue(result);
        LOGGER.info("на странице '{}' в блоке '{}' есть '{}'", pageManager.getCurrentPage().name(), elementName, text);
    }

    @Если("поле {string} отображается и пусто")
    public void checkEmptyField(String elementName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.fieldVisibleAndNoSelected(element);
        LOGGER.info("в блоке есть пустое поле '{}'", elementName);
    }
}

package steps;

import actions.Actions;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.ru.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import ru.lanit.at.utils.Sleep;

import ru.lanit.at.web.pagecontext.PageManager;

import static com.codeborne.selenide.Selenide.$;

public class EmployeeSteps {

    private PageManager pageManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeSteps.class);

    public EmployeeSteps(PageManager pageManager) {
        this.pageManager = pageManager;
    }

    @И("на текущей странице в блоке {string} нажать на любую ссылку")
    public void clickRandom(String elementName) {
        ElementsCollection elements = pageManager
                .getCurrentPage()
                .getElementsCollection(elementName);
        elements.get(Actions.getRandom(elements.size())).click();
        LOGGER.info("на странице '{}' выбран элемент '{}'", pageManager.getCurrentPage().name(), elementName);
    }

    @И("на текущей странице в блоке {string} нажать на любую кнопку {int} раз")
    public void clickRandomN(String elementName, int n) {
        ElementsCollection elements = pageManager
                .getCurrentPage()
                .getElementsCollection(elementName);
        for (int i = 0; i < n; i++) {
            elements.get(Actions.getRandom(elements.size())).click();
            LOGGER.info("В блоке '{}'было выбранно '{}' элементов", elementName, n);
        }
    }

    @И("на текущей странице в блоке {string} отжать любую кнопку {int} раз")
    public void unClickRandomN(String elementName, int n) {
        ElementsCollection elements = pageManager
                .getCurrentPage()
                .getElementsCollection(elementName);
        int i = 0;
        while (i < n) {
            int rnd = Actions.getRandom(1, elements.size() - 1);
            if (elements.get(rnd).is(Condition.checked)) {
                elements.get(rnd).click();
                i++;
            }
        }
        LOGGER.info("В блоке '{}'было отжато '{}' элементов", elementName, n);
    }

    @Затем("на текущей странице в блоке Общая информация очистить все поля: {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void clearFieldsEmployee(String surname, String name, String patronymic, String gender, String joiningDate, String birthday, String phone, String citizenship, String email) {

        SelenideElement surnameField = pageManager
                .getCurrentPage()
                .getElement(surname);
        SelenideElement nameField = pageManager
                .getCurrentPage()
                .getElement(name);
        SelenideElement patronymicField = pageManager
                .getCurrentPage()
                .getElement(patronymic);
        SelenideElement genderDropdown = pageManager
                .getCurrentPage()
                .getElement(gender);
        SelenideElement joiningDateField = pageManager
                .getCurrentPage()
                .getElement(joiningDate);
        SelenideElement birthdayField = pageManager
                .getCurrentPage()
                .getElement(birthday);
        SelenideElement phoneField = pageManager
                .getCurrentPage()
                .getElement(phone);
        SelenideElement citizenshipDropdown = pageManager
                .getCurrentPage()
                .getElement(citizenship);
        SelenideElement emailField = pageManager
                .getCurrentPage()
                .getElement(email);
        surnameField.shouldBe(Condition.visible).clear();
        nameField.shouldBe(Condition.visible).clear();
        patronymicField.shouldBe(Condition.visible).clear();
        genderDropdown.shouldBe(Condition.visible).selectOption(0);
        joiningDateField.shouldBe(Condition.visible).clear();
        birthdayField.shouldBe(Condition.visible).clear();
        phoneField.shouldBe(Condition.visible).clear();
        citizenshipDropdown.shouldBe(Condition.visible).selectOption(0);
        emailField.shouldBe(Condition.visible).clear();
        LOGGER.info("на странице '{}' имеются элементы '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}'", pageManager.getCurrentPage().name(), surname, name, patronymic, gender, joiningDate, birthday, phone, citizenship, email);
    }

    @Тогда("выбрать элемент {string} с текстом {string}")
    public void selectElementWithText(String elementName, String text) {
        pageManager
                .getCurrentPage()
                .getElement(elementName)
                .selectOption(text);
        LOGGER.info("выбран элемент '{}'", elementName);
    }

    @Затем("в блоке {string} нажать на ссылку с текстом {string}")
    @И("нажать на элемент {string} с текстом {string}")
    public void clickOnElementWithText(String elementName, String text) {
        ElementsCollection elements = pageManager
                .getCurrentPage()
                .getElementsCollection(elementName);
        elements.findBy(Condition.exactText(text)).click();
        LOGGER.info("клик на элемент '{}' с номером '{}'", elementName, text);
    }

    @И("нажать на предпоследнюю запись из {string}")
    public void clickPreLast(String elementName) {
        ElementsCollection elements = pageManager
                .getCurrentPage()
                .getElementsCollection(elementName);
        elements.get(elements.size() - 2).click();
        LOGGER.info("на текущей странице в блоке '{}' нажимается элемент '{}'", pageManager.getCurrentPage().name(), elementName);
    }

    @И("в выпадающем списке {string} выбрать {string}")
    public void setInDropDown(String listElements, String elementName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(listElements);
        element.selectOptionContainingText(elementName);
        LOGGER.info("на текущей странице в блоке '{}' нажимается элемент '{}'", pageManager.getCurrentPage().name(), element);
    }

    @И("в блоке {string} выбрать {int} {string}")
    @И("в блоке {string} выбрать значение {int} {string}")
    public void setAnything(String listOfValues, int index, String text) {
        ElementsCollection elements = pageManager
                .getCurrentPage()
                .getElementsCollection(listOfValues);
        elements.get(index).click();
        LOGGER.info("на текущей странице в блоке '{}' нажимается элемент '{}'", pageManager.getCurrentPage().name(), text);
    }

    @Тогда("в поле {string} ввести случайное значение от {int} до {int}")
    public void setRandomInt(String elementName, int start, int finish) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Actions.fillFieldRandInt(element, start, finish);
        LOGGER.info("на текущей странице в блоке '{}' введено значение в диапазоне от '{}' до '{}'", pageManager.getCurrentPage().name(), start, finish);
    }

    @Когда("кликнуть на элемент по тексту {string}")
    public void clickElementWithText(String text) {
        pageManager
                .getCurrentPage()
                .getElement(text)
                .click();
        LOGGER.info("клик на элемент по тексту '{}'", text);
    }

    @Когда("проскроллить страницу до элемента {string}")
    public void scrollToElement(String elementName) {
        SelenideElement element = pageManager.getCurrentPage().getElement(elementName);
        element.shouldBe(Condition.visible)
                .scrollIntoView("{block: 'center'}");
        LOGGER.info("скролл страницы до элемента '{}'", elementName);
    }

    @Когда("проскроллить страницу до текста {string}")
    public void scrollToText(String text) {
        SelenideElement element = $(Selectors.byText(text));
        element.shouldBe(Condition.visible)
                .scrollIntoView("{block: 'center'}");
        LOGGER.info("скролл страницы до текста '{}'", text);
    }

    @И("подождать {int} сек")
    public void waitSeconds(int timeout) {
        Sleep.pauseSec(timeout);
    }

    @Тогда("в поле {string} удалить {int} последних символа")
    public void deleteCharacter(String elementName, int numberOfChars) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        String valueOfElement = element.getValue();
        String resultString = valueOfElement.substring(0,valueOfElement.length()-numberOfChars);
        element.setValue("");
        Actions.fillInputByCharacter(element, resultString);
        LOGGER.info("из поля '{}' удалено '{}' последних символов", elementName, numberOfChars);
    }

    @Тогда("активировать в чек-лист {string} чекбокс {string}")
    public void activeCheckbox(String elementName, String checkboxName) {
        String actual = "";
        ElementsCollection elements = pageManager
                .getCurrentPage()
                .getElementsCollection(elementName);

        for (SelenideElement el : elements) {
            if (el.getText().equals(checkboxName)){
                el.click();
                actual = el.getText();
                break;
            }
        }
        Assert.assertEquals(actual, checkboxName,"Элемент не найден");
        LOGGER.info("в чек-листе '{}' активирован чекбокс '{}' - '{}'", elementName, checkboxName, actual);
    }
}

package steps;

import actions.WebActions;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.ru.Затем;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.lanit.at.web.pagecontext.PageManager;

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
        elements.get(WebActions.getRandom(elements.size())).click();
        LOGGER.info("на странице '{}' выбран элемент '{}'", pageManager.getCurrentPage().name(), elementName);
    }
    @И("на текущей странице в блоке {string} нажать на любую кнопку {int} раз")
    public void clickRandomN(String elementName,int n) {
        ElementsCollection elements = pageManager
                .getCurrentPage()
                .getElementsCollection(elementName);
        for(int i = 0; i < n ;i ++){
            elements.get(WebActions.getRandom(elements.size())).click();
            LOGGER.info("В блоке '{}'было выбранно '{}' элементов", elementName, n);
        }
    }
    @И("на текущей странице в блоке {string} отжать любую кнопку {int} раз")
    public void unClickRandomN(String elementName,int n) {
        ElementsCollection elements = pageManager
                .getCurrentPage()
                .getElementsCollection(elementName);
        int i = 0;
        while (i < n){
            int rnd = WebActions.getRandom(1,elements.size() - 1);
            if(elements.get(rnd).is(Condition.checked)){
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

    @Тогда("нажать на кнопку {string}")
    public void clickOnButton(String elementName) {
        SelenideElement element = pageManager.getCurrentPage().getElement(elementName);
        element.click();
        LOGGER.info("клик на кнопку '{}'", elementName);
    }

    @Тогда("выбрать элемент {string} с текстом {string}")
    public void selectElementWithText(String elementName, String text) {
        SelenideElement element = pageManager.getCurrentPage().getElement(elementName);
        element.selectOption(text);
        LOGGER.info("выбран элемент '{}'", elementName);
    }

    @И("нажать на элемент {string} с текстом {string}")
    public void clickOnElementWithText(String elementName, String text) {
        ElementsCollection elements = pageManager.getCurrentPage().getElementsCollection(elementName);
        elements.findBy(Condition.exactText(text)).click();
        LOGGER.info("клик на элемент '{}' с номером '{}'", elementName, text);
    }

    @И("нажать на предпоследнюю запись из {string}")
    public void clickPreLast(String elementName) {
        ElementsCollection elements = pageManager
                .getCurrentPage()
                .getElementsCollection(elementName);
        elements.get(elements.size()-2).click();
        LOGGER.info("на текущей странице в блоке '{}' нажимается элемент '{}'", pageManager.getCurrentPage().name(), elementName);
    }

    @И("в выпадающем списке {string} выбрать {string}")
    public void setCity(String listOfCities, String city) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(listOfCities);
        element.selectOptionContainingText(city);
        LOGGER.info("на текущей странице в блоке '{}' нажимается элемент '{}'", pageManager.getCurrentPage().name(), element);
    }

    @Затем("в блоке {string} нажать на ссылку с текстом {string}")
    public void selectFilter(String elementName, String text) {
        ElementsCollection elements = pageManager
                .getCurrentPage()
                .getElementsCollection(elementName);
        elements.findBy(Condition.exactText(text)).click();
        LOGGER.info("на странице '{}' выбран элемент '{}'", pageManager.getCurrentPage().name(), text);
    }

    @И("в блоке {string} выбрать {string}")
    public void setAnything(String listOfValues, String text) {
        ElementsCollection elements = pageManager
                .getCurrentPage()
                .getElementsCollection(listOfValues);
        elements.get(1).click();
        LOGGER.info("на текущей странице в блоке '{}' нажимается элемент '{}'", pageManager.getCurrentPage().name(), text);
    }

    @И("в блоке {string} выбрать значение {string}")
    public void setAnyField(String listOfValues, String text) {
        ElementsCollection elements = pageManager
                .getCurrentPage()
                .getElementsCollection(listOfValues);
        elements.get(2).click();
        LOGGER.info("на текущей странице в блоке '{}' нажимается элемент '{}'", pageManager.getCurrentPage().name(), text);
    }

    @Тогда("в поле {string} ввести случайное значение от -5 до 5")
    public void setRandomInt(String elementName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        WebActions.fillFieldRandInt(element);
    }
}

package steps;

import actions.Actions;
import authorization.AuthValues;
import authorization.Authorization;
import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import ru.lanit.at.utils.DataGenerator;
import utils.Deserializer;
import io.cucumber.java.ru.*;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import ru.lanit.at.api.testcontext.ContextHolder;
import ru.lanit.at.web.pagecontext.Environment;
import ru.lanit.at.web.pagecontext.PageManager;
import ru.lanit.at.web.pagecontext.WebPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;

import static com.codeborne.selenide.Selenide.$;

public class WebSteps {

    private static Properties properties = new Properties();
    private static final Logger LOGGER = LoggerFactory.getLogger(WebSteps.class);
    private static String currentToken = "";
    private final PageManager pageManager;

    public WebSteps(PageManager pageManager) {
        this.pageManager = pageManager;
    }

    /**
     * Открытие сайта
     */
    @Step("открытие сайта")
    @Дано("открыть сайт")
    public void openUrl() {
        loadProperties();
        Selenide.open(properties.getProperty("base.url"));
        WebDriver driver = Environment.getDriver();
        if (driver == null) {
            WebDriver currentThreadWebDriver = WebDriverRunner.getWebDriver();
            Environment.setThreadDriver(currentThreadWebDriver);
        }
        LOGGER.info("инициализация webdriver для потока: {}", Thread.currentThread().getId());
    }

    private static void loadProperties() {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("src/main/resources/application.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Инициализация страницы
     *
     * @param pageName - наименование элемента
     */
    @Step("инициализация страницы {string}")
    @И("переход на страницу {string}")
    public void setPage(String pageName) {
        WebPage page = Environment.getPage(pageName);
        pageManager.setCurrentPage(page);
    }

    /**
     * Авторизация пользователя
     *
     * @param login - логин пользователя
     */
    @Step("авторизация под логином {login}")
    @Если("авторизоваться логином {string}")
    public void authWithLogin(String login) {
        Deserializer deserializer = new Deserializer();
        Authorization auth = deserializer.yamlDeserialize(properties.getProperty("auth.yaml"));

        for (AuthValues el : auth.getAuthValues()) {
            if (el.getLogin().equals(login) && el.isToken().equals(true)) {
                clickOnCheckbox("Я желаю войти с админскими правами");
                fillField("логин", el.getLogin());
                fillField("пароль", el.getPassword());
                fillTokenField("токен", el.getLogin(), el.getPassword());
                clickSignInButton("войти");

            } else if (el.getLogin().equals(login) && el.isToken().equals(false)) {
                fillField("логин", el.getLogin());
                fillField("пароль", el.getPassword());
                clickSignInButton("войти");
            }
        }
        LOGGER.info("авторизация под логином: '{}'", login);
    }

    public void fillTokenField(String elementName, String login, String password) {
        ApiSteps.getToken(login, password);
        String token = ContextHolder.getValue("TOTP").toString();
        if (token.equals(currentToken)) {
            fillTokenField(elementName, login, password);
        } else {
            SelenideElement element = pageManager.getCurrentPage().getElement(elementName);
            element.setValue(token);
            currentToken = token;
            LOGGER.info("в поле '{}' введено значение '{}'", elementName, token);
        }
    }

    /**
     * Клик по любой ссылке в блоке
     *
     * @param elementName - название блока
     */
    @Step("на текущей странице в блоке {elementName} нажать на любую ссылку")
    @И("на текущей странице в блоке {string} нажать на любую ссылку")
    public void clickRandom(String elementName) {
        ElementsCollection elements = pageManager
                .getCurrentPage()
                .getElementsCollection(elementName);
        elements.get(Actions.getRandom(elements.size())).click();
        LOGGER.info("на странице '{}' выбран элемент '{}'", pageManager.getCurrentPage().name(), elementName);
    }

    /**
     * Активация чекбокса
     *
     * @param elementName - название чекбокса
     */
    @Step("нажатие на чекбокс {elementName}")
    @Тогда("нажать на чекбокс {string}")
    public void clickOnCheckbox(String elementName) {
        SelenideElement element = pageManager.getCurrentPage().getElement(elementName);
        element.click();
        LOGGER.info("активация чекбокса '{}'", elementName);
    }

    /**
     * Заполнение поля значением
     *
     * @param elementName - название поля
     * @param value - значение
     */
    @Step("заполнение поля {elementName} значением {value}")
    @Тогда("заполнить поле {string} значением {string}")
    public void fillField(String elementName, String value) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        element.setValue(value);
        LOGGER.info("в поле '{}' введено значение '{}'", elementName, value);
    }

    /**
     * Заполнение поля случайной строкой
     *
     * @param field - название поля
     */
    @Step("заполнение поля {field} случайной строкой")
    @И("заполнить поле {string} случайной строкой")
    public void fillFieldRandomString(String field) {
        String randomString = "EXAMPLE_" + UUID.randomUUID().toString();
        fillField(field, randomString);
        ContextHolder.put(field, randomString);
    }

    /**
     * Сохранение содержимого поля в ContextHolder
     *
     * @param fieldName - название поля
     */
    @Step("сохранение содержимого поля {fieldName} в ContextHolder")
    @И("сохранить содержимое поля {string} в ContextHolder")
    public void saveFieldContents(String fieldName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(fieldName);
        String value = element.getValue();
        ContextHolder.put(fieldName, value);
        LOGGER.info("содержимое поля '{}' - '{}' сохранено в ContextHolder", fieldName,value);
    }

    /**
     * Сохранение значения выпадающего списка в ContextHolder
     *
     * @param fieldName - название списка
     */
    @Step("сохранение выделенного значения выпадающего списка {fieldName} в ContextHolder")
    @И("сохранить выделенное значение выпадающего списка {string} в ContextHolder")
    public void saveDropDownListContents(String fieldName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(fieldName);
        String value = element.getSelectedText();
        ContextHolder.put(fieldName, value);
        LOGGER.info("содержимое поля '{}' - '{}' сохранено в ContextHolder", fieldName,value);
    }

    /**
     * Сохранение значения выпадающего списка в ContextHolder
     *
     * @param fieldName - название списка
     */
    @Step("сравнение значения поля {fieldName} и содержимого ContextHolder")
    @И("сравнить значение поля {string} и содержимое ContextHolder")
    public void checkField(String fieldName) {
        String expectedValue = ContextHolder.getValue(fieldName).toString();
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(fieldName);
        String actualValue = element.getValue();
        Assert.assertEquals(actualValue, expectedValue);
        LOGGER.info("Ожидаемое значение поля: '{}', актуальное значения поля: {}", expectedValue, actualValue);
    }

    /**
     * Сравнение текста выделенного поля и содержимого ContextHolder
     *
     * @param fieldName - название поля
     */
    @Step("сравнение текста выделенного поля {fieldName} и содержимое ContextHolder")
    @И("сравнить текст выделенного поля {string} и содержимое ContextHolder")
    public void checkSelectedField(String fieldName) {
        String expectedValue = ContextHolder.getValue(fieldName).toString();
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(fieldName);
        String actualValue = element.getSelectedText();
        Assert.assertEquals(actualValue, expectedValue);
        LOGGER.info("Содержимое поля - {}", actualValue);
    }

    /**
     * Клик по кнопке
     *
     * @param elementName - название кнопки
     */
    @Step("клик по кнопке {elementName}")
    private void clickSignInButton(String elementName) {
        SelenideElement element = pageManager.getCurrentPage().getElement(elementName);
        element.click();
        LOGGER.info("клик по кнопке '{}'", elementName);
    }

    /**
     * Клик по ссылке с текстом
     *
     * @param elementName - ссылка
     * @param text - текст
     */
    @Step("нажатие на ссылку {elementName} с текстом {text}")
    @И("нажать на ссылку {string} с текстом {string}")
    public void clickOnElementWithText(String elementName, String text) {
        ElementsCollection elements = pageManager
                .getCurrentPage()
                .getElementsCollection(elementName);
        elements.findBy(Condition.exactText(text)).click();
        LOGGER.info("клик на ссылку '{}' с текстом '{}'", elementName, text);
    }

    /**
     * Клик по элементу с текстом
     *
     * @param text - текст
     */
    @Step("клик на элемент по тексту {text}")
    @Когда("кликнуть на элемент по тексту {string}")
    public void clickElementWithText(String text) {
        pageManager
                .getCurrentPage()
                .getElement(text)
                .click();
        LOGGER.info("клик на элемент по тексту '{}'", text);
    }

    /**
     * Активация нескольких кнопок в блоке
     *
     * @param elementName - название блока
     * @param n - количество раз активации
     */
    @Step("на текущей странице в блоке {elementName} происходит нажатие на любую кнопку {n} раз")
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

    /**
     * Деактивация нескольких кнопок в блоке
     *
     * @param elementName - название блока
     * @param n - количество раз деактивации
     */
    @Step("на текущей странице в блоке {elementName} происходит деактивация любой кнопки {n} раз")
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
        LOGGER.info("В блоке '{}'было деакьивировано '{}' элементов", elementName, n);
    }

    /**
     * Очистка нескольких полей
     *
     * @param surname - название поля
     * @param name - название поля
     * @param patronymic - название поля
     * @param gender - название поля
     * @param joiningDate - название поля
     * @param birthday - название поля
     * @param phone - название поля
     * @param citizenship - название поля
     * @param email - название поля
     */
    @Step("на текущей странице в блоке Общая информация очищаются поля: {surname}, " +
            "{name}, {patronymic}, {gender}, {joiningDate}, {birthday}, {phone}, " +
            "{citizenship}, {email}")
    @Затем("на текущей странице в блоке Общая информация очистить все поля: {string}, " +
            "{string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void clearFieldsEmployee(String surname, String name, String patronymic,
                                    String gender, String joiningDate, String birthday,
                                    String phone, String citizenship, String email) {

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

    /**
     * Выбор элемента с текстом
     *
     * @param elementName - название элемента
     * @param text - текст
     */
    @Step("выбор элемента {elementName} с текстом {text}")
    @Тогда("выбрать элемент {string} с текстом {string}")
    public void selectElementWithText(String elementName, String text) {
        pageManager.getCurrentPage().getElement(elementName).selectOption(text);
        LOGGER.info("выбран элемент '{}'", elementName);
    }

    /**
     * Клик по предпоследней записи из списка
     *
     * @param elementName - название списка
     */
    @Step("нажатие на предпоследнюю запись из списка {elementName}")
    @И("нажать на предпоследнюю запись из {string}")
    public void clickPreLast(String elementName) {
        ElementsCollection elements = pageManager
                .getCurrentPage()
                .getElementsCollection(elementName);
        elements.get(elements.size() - 2).click();
        LOGGER.info("на текущей странице нажимается элемент '{}'", elementName);
    }

    /**
     * Выбор значения в выпадающем списке по содержащемуся тексту
     *
     * @param listElements - название выпадающего списка
     * @param elementName - значение
     */
    @Step("в выпадающем списке {listElements} выбирается {elementName}")
    @И("в выпадающем списке {string} выбрать {string}")
    public void setInDropDown(String listElements, String elementName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(listElements);
        element.selectOptionContainingText(elementName);
        LOGGER.info("в выпадающем списке '{}' нажимается элемент '{}'", pageManager.getCurrentPage().name(), element);
    }

    /**
     * Выбор значения в выпадающем списке по содержащемуся тексту
     *
     * @param listOfValues - название выпадающего списка
     * @param index - индекс
     */
    @Step("в выпадающем списке {listOfValues} выбирается значение по индексу {text}")
    @И("в блоке {string} выбрать значение {int} {string}")
    public void setAnything(String listOfValues, int index) {
        ElementsCollection elements = pageManager
                .getCurrentPage()
                .getElementsCollection(listOfValues);
        elements.get(index).click();
        LOGGER.info("в блоке '{}' нажимается элемент по индексу '{}'", pageManager.getCurrentPage().name(), index);
    }

    /**
     * Выбор случайного значения в списке
     *
     * @param elementName - название списка
     */
    @Step("в списке {elementName} выбирается случайный элемент")
    @Если("в списке {string} выбрать случайный элемент")
    public void listSelectRandElement(String elementName){
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        element.selectOption( 1 + (int) (Math.random() * element.findAll(By.cssSelector("option")).size()-1));
        String selectedText = element.getSelectedText();
        LOGGER.info("в списке '{}' выбран элемент со случайным значением - '{}'", elementName, selectedText);
    }

    /**
     * Ввод в поле случайного значения в заданном диапазоне
     *
     * @param elementName - название поля
     * @param start - начало диапазона
     * @param finish - конец диапазона
     */
    @Step("в поле {elementName} вводится случайное значение в диапазоне от {start} до {finish}")
    @Тогда("в поле {string} ввести случайное значение от {int} до {int}")
    public void setRandomInt(String elementName, int start, int finish) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Actions.fillFieldRandInt(element, start, finish);
        LOGGER.info("на текущей странице в блоке '{}' введено значение в диапазоне от '{}' до '{}'", pageManager.getCurrentPage().name(), start, finish);
    }

    /**
     * Проскролливание страницы до элемента
     *
     * @param elementName - название элемента
     */
    @Step("проскролливание страницы до элемента {elementName}")
    @Когда("проскроллить страницу до элемента {string}")
    public void scrollToElement(String elementName) {
        SelenideElement element = pageManager.getCurrentPage().getElement(elementName);
        element.shouldBe(Condition.visible)
                .scrollIntoView("{block: 'center'}");
        LOGGER.info("скролл страницы до элемента '{}'", elementName);
    }

    /**
     * Проскролливание страницы до текста
     *
     * @param text - текст
     */
    @Step("проскролливание страницы до текста {text}")
    @Когда("проскроллить страницу до текста {string}")
    public void scrollToText(String text) {
        SelenideElement element = $(Selectors.byText(text));
        element.shouldBe(Condition.visible)
                .scrollIntoView("{block: 'center'}");
        LOGGER.info("скролл страницы до текста '{}'", text);
    }

    /**
     * Удаление в поле нескольких последних символов
     *
     * @param elementName - название поля
     * @param numberOfChars - количество символов
     */
    @Step("в поле {elementName} удаляются {numberOfChars} последних символа")
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

    /**
     * Активация чекбокса в чек-листе
     *
     * @param elementName - название чек-листа
     * @param checkboxName - название чекбокса
     */
    @Step("активация в чек-листе {elementName} чекбокса {checkboxName}")
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

    /**
     * Очистка одного поля
     *
     * @param elementName - название поля
     */
    @Step("очистка поля {elementName}")
    @Если("очистить поле {string}")
    public void clearFiled(String elementName) {
        pageManager
                .getCurrentPage()
                .getElement(elementName)
                .shouldBe(Condition.visible)
                .clear();
    }

    /**
     * Загрузка файла
     *
     * @param elementName - название поля
     * @param path - название файла
     */
    @Step("на текущей странице в поле {elementName} загружается файл {path}")
    @Затем("на текущей странице в поле {string} загрузить файл {string}")
    public void uploadFiles(String elementName, String path) {
        SelenideElement uploadElement = pageManager
                .getCurrentPage()
                .getElement(elementName);

        uploadElement.uploadFile(new File("src/test/resources/files/" + path));

        LOGGER.info("на странице '{}' загружено изображение '{}'", pageManager.getCurrentPage().name(), elementName);
    }

    /**
     * Ввод значения в элемент по маске
     *
     * @param field - наименование элемента
     * @param mask - значение
     */
    @Step("ввод в поле {field} случайное значение по маске {mask}")
    @Когда("ввести в поле {string} случайное значение по маске {string}")
    public void generateFillTheField(String field, String mask) {
        SelenideElement fieldElement = pageManager
                .getCurrentPage()
                .getElement(field);
        String value = DataGenerator.generateValueByMask(mask);
        fieldElement
                .shouldBe(Condition.visible)
                .setValue(value);
        LOGGER.info("ввести в поле {} случайное значение {}", field, value);
    }

    /**
     * Закрытие страницы
     *
     */
    @Step("закрытие страницы")
    @Если("закрыть страницу")
    public void closeDriver() {
        WebDriverRunner.getWebDriver().close();
    }

}

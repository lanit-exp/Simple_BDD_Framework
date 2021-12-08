package steps;

import com.codeborne.selenide.*;
import authorization.AuthValues;
import authorization.Authorization;
import utils.Deserializer;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.ru.*;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import ru.lanit.at.api.testcontext.ContextHolder;
import ru.lanit.at.web.pagecontext.Environment;
import ru.lanit.at.web.pagecontext.PageManager;
import ru.lanit.at.web.pagecontext.WebPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;

public class AuthorizationSteps {

    private static Properties properties = new Properties();
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationSteps.class);
    private static String currentToken = "";
    private final PageManager pageManager;

    public AuthorizationSteps(PageManager pageManager) {
        this.pageManager = pageManager;
    }

    @Deprecated
    @Если("открыть url {string}")
    @Дано("открыть {string}")
    public void openUrl(String url) {
        Selenide.open(url);
        WebDriver driver = Environment.getDriver();
        if (driver == null) {
            WebDriver currentThreadWebDriver = WebDriverRunner.getWebDriver();
            Environment.setThreadDriver(currentThreadWebDriver);
        }
        LOGGER.info("инициализация webdriver для потока: {}", Thread.currentThread().getId());
    }

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

    @Тогда("нажать на чекбокс {string}")
    public void clickOnCheckbox(String elementName) {
        SelenideElement element = pageManager.getCurrentPage().getElement(elementName);
        element.click();
        LOGGER.info("клик на элемент по тексту '{}'", elementName);
    }

    @Тогда("заполнить поле {string} значением {string}")
    public void fillField(String elementName, String value) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        element.setValue(value);
        LOGGER.info("в поле '{}' введено значение '{}'", elementName, value);
    }

    @И("заполнить поле {string} случайной строкой")
    public void fillFieldRandomString(String field) {
        String randomString = "EXAMPLE_" + UUID.randomUUID().toString();
        fillField(field, randomString);
        ContextHolder.put(field, randomString);
    }

    @И("сохранить содержимое поля {string} в ContextHolder")
    public void saveFieldContents(String fieldName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(fieldName);
        String value = element.getValue();
        ContextHolder.put(fieldName, value);
        LOGGER.info("содержимое поля '{}' - '{}' сохранено в ContextHolder", fieldName,value);
    }

    @И("сохранить выделенное значение выпадающего списка {string} в ContextHolder")
    public void saveDropDownListContents(String fieldName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(fieldName);
        String value = element.getSelectedText();
        ContextHolder.put(fieldName, value);
        LOGGER.info("содержимое поля '{}' - '{}' сохранено в ContextHolder", fieldName,value);
    }

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

    private void clickSignInButton(String elementName) {
        SelenideElement element = pageManager.getCurrentPage().getElement(elementName);
        element.click();
        LOGGER.info("клик на элемент по тексту '{}'", elementName);
    }

    @Тогда("инициализация страницы {string}")
    @И("переход на страницу {string}")
    public void setPage(String pageName) {
        WebPage page = Environment.getPage(pageName);
        pageManager.setCurrentPage(page);
    }

    @Тогда("ввести {string} для пользователя {string} с паролем {string}")
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

    private static void loadProperties() {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("src/main/resources/application.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

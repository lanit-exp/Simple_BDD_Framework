package steps;

import com.codeborne.selenide.*;
import io.cucumber.java.ru.*;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import ru.lanit.at.api.testcontext.ContextHolder;
import ru.lanit.at.web.pagecontext.Environment;
import ru.lanit.at.web.pagecontext.PageManager;
import ru.lanit.at.web.pagecontext.WebPage;

import java.util.UUID;

public class AuthorizationSteps {

    private static final Logger LOG = LoggerFactory.getLogger(AuthorizationSteps.class);
    private static String currentToken = "";
    private final PageManager pageManager;

    public AuthorizationSteps(PageManager pageManager) {
        this.pageManager = pageManager;
    }

    @Дано("открыть {string}")
    public void openUrl(String url) {
        Selenide.open(url);
        WebDriver driver = Environment.getDriver();
        if (driver == null) {
            WebDriver currentThreadWebDriver = WebDriverRunner.getWebDriver();
            Environment.setThreadDriver(currentThreadWebDriver);
        }
        LOG.info("init webdriver for thread: {}", Thread.currentThread().getId());
    }

    @Тогда("нажать на чекбокс {string}")
    public void clickOnCheckbox(String elementName) {
        SelenideElement element = pageManager.getCurrentPage().getElement(elementName);
        element.click();
        LOG.info("клик на элемент по тексту '{}'", elementName);
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
        LOG.info("в чек-листе '{}' активирован чекбокс '{}' - '{}'", elementName, checkboxName, actual);
    }

    @Тогда("заполнить поле {string} значением {string}")
    public void fillField(String elementName, String value) {
        SelenideElement element = pageManager.getCurrentPage().getElement(elementName);
        element.setValue(value);
        LOG.info("в поле '{}' введено значение '{}'", elementName, value);
    }

    @И("заполнить поле {string} случайной строкой")
    public void fillFieldRandomString(String field) {
        String randomString = "EXAMPLE_" + UUID.randomUUID().toString();
        fillField(field,randomString );
        ContextHolder.put(field, randomString);
    }

    @И("сохранить содержимое поля {string} в ContextHolder")
    public void saveFieldContents(String fieldName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(fieldName);
        String value = element.getValue();
        ContextHolder.put(fieldName, value);
        LOG.info("содержимое поля '{}' - '{}' сохранено в ContextHolder", fieldName,value);
    }

    @И("сохранить выделенное значение выпадающего списка {string} в ContextHolder")
    public void saveDropDownListContents(String fieldName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(fieldName);
        String value = element.getSelectedText();
        ContextHolder.put(fieldName, value);
        LOG.info("содержимое поля '{}' - '{}' сохранено в ContextHolder", fieldName,value);
    }

    @И("сравнить значение поля {string} и содержимое ContextHolder")
    public void checkField(String fieldName) {
        String expectedValue = ContextHolder.getValue(fieldName).toString();
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(fieldName);
        String actualValue = element.getValue();
        Assert.assertEquals(actualValue, expectedValue);
       LOG.info("Содержимое поля - {}", actualValue);
    }
    @И("сравнить текст выделенного поля {string} и содержимое ContextHolder")
    public void checkSelectedField(String fieldName) {
        String expectedValue = ContextHolder.getValue(fieldName).toString();
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(fieldName);
        String actualValue = element.getSelectedText();
        Assert.assertEquals(actualValue, expectedValue);
        LOG.info("Содержимое поля - {}", actualValue);
    }

    @Тогда("нажать на {string}")
    public void clickSignInButton(String elementName) {
        SelenideElement element = pageManager.getCurrentPage().getElement(elementName);
        element.click();
        LOG.info("клик на элемент по тексту '{}'", elementName);
    }

    @Тогда("инициализация страницы {string}")
    @И("переход на страницу {string}")
    public void setPage(String pageName) {
        WebPage page = Environment.getPage(pageName);
        pageManager.setCurrentPage(page);
    }

    @Тогда("ввести {string} для пользователя {string} с паролем {string}")
    public void fillFieldToken(String elementName, String login, String password) {
        ApiSteps.getToken(login, password);
        String token = ApiSteps.getCurrentToken();
        if (token.equals(currentToken)) {
            fillFieldToken(elementName, login, password);
        } else {
            SelenideElement element = pageManager.getCurrentPage().getElement(elementName);
            element.setValue(token);
            currentToken = token;
            LOG.info("в поле '{}' введено значение '{}'", elementName, token);
        }
    }
}

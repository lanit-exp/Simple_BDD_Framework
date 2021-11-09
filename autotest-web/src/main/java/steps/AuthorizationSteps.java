package steps;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.ru.*;
import io.restassured.path.json.JsonPath;
import net.minidev.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.lanit.at.api.testcontext.ContextHolder;
import ru.lanit.at.web.pagecontext.Environment;
import ru.lanit.at.web.pagecontext.PageManager;
import ru.lanit.at.web.pagecontext.WebPage;

import static io.restassured.RestAssured.given;

public class AuthorizationSteps {

    private static final Logger LOG = LoggerFactory.getLogger(AuthorizationSteps.class);
    private final PageManager pageManager;
    private final Logger LOGGER = LoggerFactory.getLogger(WindowSteps.class);

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
        LOGGER.info("init webdriver for thread: {}", Thread.currentThread().getId());
    }

    @Если("деактивировано поле {string}")

    @Тогда("нажать на чекбокс {string}")
    public void clickOnCheckbox(String elementName) {
        SelenideElement element = pageManager.getCurrentPage().getElement(elementName);
        element.click();
        LOGGER.info("клик на элемент по тексту '{}'", elementName);
    }

    @Тогда("заполнить поле {string} значением {string}")
    public void fillField(String elementName, String value) {
        SelenideElement element = pageManager.getCurrentPage().getElement(elementName);
        element.setValue(value);
        LOGGER.info("в поле '{}' введено значение '{}'", elementName, value);
    }

    @И("заполнить поле {string} значением {string}")
    public void fillFieldPass(String elementName, String value) {
        SelenideElement element = pageManager.getCurrentPage().getElement(elementName);
        element.setValue(value);
        LOGGER.info("в поле '{}' введено значение '{}'", elementName, value);
    }

    @И("получить Token для юзера {string} с паролем {string}")
    public void getToken(String username,String password) {
        JSONObject innerBody = new JSONObject();
        innerBody.put("username", username);
        innerBody.put("password", password);
        JsonPath tokenJson = given()
                .baseUri("http://178.154.246.238:58082/")
                .contentType("application/json")
                .body(innerBody)
                .when()
                .post("api/otp_token/")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath();
        ContextHolder.put("TOTP", tokenJson.get("otp_token").toString());
        LOG.info("Токен для авторизации - {}", ContextHolder.getValue("TOTP").toString());
    }

    @Тогда("нажать на кнопку {string}")
    public void clickSignInButton(String elementName) {
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
}

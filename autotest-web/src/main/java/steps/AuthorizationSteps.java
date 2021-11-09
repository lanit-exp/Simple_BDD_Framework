package steps;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Тогда;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.lanit.at.web.pagecontext.Environment;
import ru.lanit.at.web.pagecontext.PageManager;

public class AuthorizationSteps {

    private PageManager pageManager;
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

    //    Тогда нажать на чекбокс 'Я желаю войти с админскими правами'
    //    Тогда нажать на чекбокс 'Я здесь впервые'
    @Тогда("нажать на чекбокс {string}")
    void clickOnCheckbox(String elementName) {
        SelenideElement element = pageManager.getCurrentPage().getElement(elementName);
        element.click();
    }

//    Затем заполнить поле 'логин' значением 'login'
//    Затем заполнить поле 'пароль' значением 'password'
    @Тогда("заполнить поле {string} значением {string}")
    void fillField(String elementName, String value) {
        SelenideElement element = pageManager.getCurrentPage().getElement(elementName);
        element.setValue(value);
        LOGGER.info("в поле '{}' введено значение '{}'", elementName, value);
    }

    String getToken(String username, String password) {
        return null;
    }

//    Тогда нажать на кнопку 'Войти'
//    Тогда нажать на кнопку 'Выслать инструкцию на почту'
    @Тогда("нажать на кнопку {string}")
    void clickSignInButton(String elementName) {
        SelenideElement element = pageManager.getCurrentPage().getElement(elementName);
        element.click();
    }
}

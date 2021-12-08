package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.ru.Дано;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.lanit.at.web.annotations.Name;
import ru.lanit.at.web.pagecontext.Environment;
import ru.lanit.at.web.pagecontext.WebPage;
import steps.AuthorizationSteps;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

    @Name(value = "DjangoAuthorization")
    public class DjangoAuthorization extends WebPage {

        private static Properties properties = new Properties();
        private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationSteps.class);
        private static String currentToken = "";


        @Name("логин")
        private SelenideElement usernameField = $(By.id("id_username"));

        @Name("пароль")
        private SelenideElement passwordField = $(By.id("id_password"));

        @Name("токен")
        private SelenideElement tokenField = $(By.id("id_otp_token"));

        @Name("Я желаю войти с админскими правами")
        private SelenideElement isAdminCheckbox = $(By.id("id_i_am_admin"));

        @Name("Я здесь впервые")
        private SelenideElement haveTokenCheckbox = $(By.id("id_i_have_no_token"));

        @Name("войти")
        private SelenideElement signinButton = $x("//input[@value='Войти']");

        @Name("выслать инструкцию на почту")
        private SelenideElement sendByEmailButton = $x("//input[@value='Выслать инструкцию на почту']");

        @Step("Открываем сайт")
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





    }


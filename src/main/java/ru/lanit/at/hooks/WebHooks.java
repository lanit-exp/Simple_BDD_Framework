package ru.lanit.at.hooks;

import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static ru.lanit.at.assertion.AssertsManager.getAssertsManager;

public class WebHooks {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebHooks.class);

    @Before
    public void setup(Scenario scenario) {
    }

    @After
    public void close() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            LOGGER.info("Закрытие сессии драйвера");
            WebDriverRunner.closeWebDriver();
        }
        getAssertsManager().softAssert().assertAll();
        getAssertsManager().softAssert().flush();
    }
}

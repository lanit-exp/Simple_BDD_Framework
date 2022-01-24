package hooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import ru.lanit.at.utils.ErrorMessage;
import ru.lanit.at.web.pagecontext.Environment;
import ru.lanit.at.web.properties.WebConfigurations;

import java.util.concurrent.TimeUnit;

public class WebHooks {

    @Before
    public void setup() {
        WebConfigurations cfg = ConfigFactory.create(WebConfigurations.class,
                System.getProperties(),
                System.getenv());

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
        );

        switch (cfg.webDriverBrowserName()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                break;
            default: {
                throw new IllegalArgumentException(String.format(ErrorMessage.BROWSER_NOT_SUPPORTED, cfg.webDriverBrowserName()));
            }
        }

        Configuration.browser = cfg.webDriverBrowserName();
        Configuration.browserSize = cfg.webDriverBrowserSize();
        Configuration.browserVersion = cfg.webDriverVersion();
        Configuration.savePageSource = true;
        Configuration.screenshots = true;
        Configuration.webdriverLogsEnabled = false;
        Configuration.timeout = TimeUnit.SECONDS.toMillis(cfg.webDriverTimeoutSeconds());
        Configuration.pollingInterval = cfg.pollingTimeoutMs();
        Configuration.reportsFolder = System.getProperty("selenide.report.folder");
        Configuration.downloadsFolder = System.getProperty("selenide.download.folder");
        Environment.initPages(cfg.pagesPackage());
    }

    @After
    public void close() {
        WebDriverRunner.closeWebDriver();
        Environment.demountDriver();
    }
}

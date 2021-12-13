package steps.will_be_removed;

import actions.will_be_removed.WebActions;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.ru.Тогда;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.lanit.at.api.testcontext.ContextHolder;
import ru.lanit.at.web.pagecontext.Environment;
import ru.lanit.at.web.pagecontext.PageManager;
import steps.ApiSteps;

@Deprecated
public class WindowSteps {

    private PageManager pageManager;
    private final Logger LOGGER = LoggerFactory.getLogger(WindowSteps.class);

    public WindowSteps(PageManager manager) {
        this.pageManager = manager;
    }

    /**
     * открывает страницу по ссылке
     *
     * @param url url
     */
//    @Если("открыть url {string}")
    public void open(String url) {
        Selenide.open(url);
        WebDriver driver = Environment.getDriver();
        if (driver == null) {
            WebDriver currentThreadWebDriver = WebDriverRunner.getWebDriver();
            Environment.setThreadDriver(currentThreadWebDriver);
        }
        LOGGER.info("init webdriver for thread: {}", Thread.currentThread().getId());
    }

    /**
     * открывает новую вкладку в браузере с url и переключается на нее
     *
     * @param url url
     */
//    @И("открыть новую вкладку с url {string}")
    public void openNewTab(String url) {
        WebActions.openUrlOnNewTab(url);
    }

    /**
     * если вкладок 2, то переключится на следующую вкладку.
     * <br/>по факту переключается на последнюю вкладку
     */
//    @И("переключиться на следующую вкладку")
    public void switchNextTab() {
        WebActions.switchToNextTab(null);
    }

    /**
     * переключается на вкладку по порядковому номеру
     *
     * @param number порядковый номер вкладки в браузере
     */
//    @И("пеерключиться на вкладку по порядковому номеру {int}")
    public void switchNextTabByNumber(int number) {
        WebActions.switchToNextTab(number);
    }

    /**
     * закрывает текущую вкладку и переходит на предыдущую (последняя вкладка в наборе)
     */
//    @И("закрыть текущую вкладку и перейти на предыдущую")
    public void closeTabAndSwitchTab() {
        WebActions.closeCurrentTabAndSwitchToPrevious();
    }

    /**
     * закрывает страницу
     */
//    @Если("закрыть страницу")
    public void closeDriver() {
        WebDriverRunner.getWebDriver().close();
    }
}


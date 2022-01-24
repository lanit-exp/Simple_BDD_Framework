package actions;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import ru.lanit.at.utils.ErrorMessage;
import ru.lanit.at.utils.Sleep;
import ru.lanit.at.web.pagecontext.Environment;

import java.util.ArrayList;
import java.util.List;


public class WebActions {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebActions.class);
    private static final WebDriver DRIVER = Environment.getDriver();

    /**
     * Открывает ссылку по переданному url и переводит контекст драйвера на новое окно
     */
    public static void openUrlOnNewTab(String url) {
        String command = String.format("window.open('%s')", url);
        Selenide.executeJavaScript(command);
        List<String> handles = new ArrayList<>(DRIVER.getWindowHandles());
        Selenide.switchTo().window(handles.get(handles.size() - 1));
        LOGGER.info("Отрытие новой вкладки с урл {}", url);
    }

    /**
     * Переключается на следующую вкладку или вкладку по порядковому номеру (1, 2, ...)
     */
    public static void switchToNextTab(Integer tabNumber) {
        List<String> handles = new ArrayList<>(DRIVER.getWindowHandles());
        if (tabNumber != null) {
            Assert.assertTrue(handles.size() >= tabNumber, String.format(ErrorMessage.BROWSER_TAB_NUMBER_MORE_THAN_TABS, tabNumber));
        }
        tabNumber = tabNumber == null ? handles.size() - 1 : tabNumber - 1;
        Selenide.switchTo().window(handles.get(tabNumber));
        LOGGER.info("Переключение на вкладку {}", handles.size());
    }

    /**
     * Закрывает текущую вкладку и переключается на предыдущую
     */
    public static void closeCurrentTabAndSwitchToPrevious() {
        Selenide.closeWindow();
        List<String> handles = new ArrayList<>(DRIVER.getWindowHandles());
        Selenide.switchTo().window(handles.get(handles.size() - 1));
    }

    /**
     * Посимвольное заполнение поля
     *
     * @param element - элемент
     * @param text    - значение
     */
    public static void fillInputByCharacter(SelenideElement element, String text) {
        for (char character : text.toCharArray()) {
            element.sendKeys(String.valueOf(character));
            Sleep.pauseSec(0.2);
        }
    }
}

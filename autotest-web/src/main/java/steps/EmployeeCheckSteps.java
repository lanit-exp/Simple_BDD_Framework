package steps;

import actions.Checks;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.И;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.lanit.at.web.pagecontext.PageManager;

public class EmployeeCheckSteps {

    private PageManager pageManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeCheckSteps.class);

    public EmployeeCheckSteps(PageManager pageManager) {
        this.pageManager = pageManager;
    }

    @Если("на текущей странице {string} с текстом {string} присутствует")
    @И("поле {string} инфоблок с текстом {string} присутствует")
    public void matchText(String elementName, String text) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.elementTextEqualsExpectedText(element, text);
        LOGGER.info("на странице '{}' имеется элемент '{}'", pageManager.getCurrentPage().name(), elementName);
    }
}

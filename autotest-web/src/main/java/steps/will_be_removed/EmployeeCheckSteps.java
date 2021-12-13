package steps.will_be_removed;

import actions.Checks;
import com.codeborne.selenide.SelenideElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.lanit.at.web.pagecontext.PageManager;

@Deprecated
public class EmployeeCheckSteps {

    private PageManager pageManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeCheckSteps.class);

    public EmployeeCheckSteps(PageManager pageManager) {
        this.pageManager = pageManager;
    }

//    @Если("в текущем блоке поле {string} заблокировано")
    public void checkBlockedField(String elementName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.emptyElement(element);
        LOGGER.info("элемент '{}' заблокирован", elementName);
    }

}

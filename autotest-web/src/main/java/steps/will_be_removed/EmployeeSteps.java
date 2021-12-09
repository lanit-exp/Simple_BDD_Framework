package steps.will_be_removed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.lanit.at.utils.Sleep;

import ru.lanit.at.web.pagecontext.PageManager;

import static com.codeborne.selenide.Selenide.$;

@Deprecated
public class EmployeeSteps {

    private PageManager pageManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeSteps.class);

    public EmployeeSteps(PageManager pageManager) {
        this.pageManager = pageManager;
    }

 //   @И("подождать {int} сек")
    public void waitSeconds(int timeout) {
        Sleep.pauseSec(timeout);
    }

}

package actions;

import com.codeborne.selenide.SelenideElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.lanit.at.web.pagecontext.PageManager;
import steps.AuthorizationCheckSteps;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Services {

    private PageManager pageManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(Services.class);

    public void uploadFiles(String elementName, String path) {
        SelenideElement uploadElement = pageManager
                .getCurrentPage()
                .getElement(elementName);
        uploadElement.setValue(path).pressEnter();
        LOGGER.info("на странице '{}' загружено изображение '{}'", pageManager.getCurrentPage().name(), elementName);
    }

    public void downloadFiles(String elementName) {

        SelenideElement downloadElement = pageManager
                .getCurrentPage()
                .getElement(elementName);

        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);


        LOGGER.info("на странице '{}' скачано изображение '{}'", pageManager.getCurrentPage().name(), elementName);
    }
}

package steps.will_be_removed;

import actions.Checks;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.lanit.at.web.pagecontext.PageManager;

@Deprecated
public class AuthorizationCheckSteps {

    private PageManager pageManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationCheckSteps.class);

    public AuthorizationCheckSteps(PageManager pageManager) {
        this.pageManager = pageManager;
    }

    /**
     * ожидание исчезновения текста на странице в течение некоторого времени
     *
     * @param text           текст
     * @param timeoutSeconds количество секунд
     */
 //   @Когда("подождать исчезновения текста {string} в течение {int} секунд")
    public void waitUntilTextAbsentOnPage(String text, int timeoutSeconds) {
        Checks.textAbsentOnPage(text, timeoutSeconds);
        LOGGER.info("на странице '{}' отсутствует текст '{}'", pageManager.getCurrentPage().name(), text);
    }

    /**
     * ожидание появления текста на странице в течении некоторого времени
     *
     * @param text           текст
     * @param timeoutSeconds количество секунд
     */
 //   @Когда("подождать появления текста {string} в течение {int} секунд")
    public void waitUntilTextAppearOnPage(String text, int timeoutSeconds) {
        Checks.textVisibleOnPage(text, timeoutSeconds);
        LOGGER.info("на странице '{}' имеется текст '{}'", pageManager.getCurrentPage().name(), text);
    }

    /**
     * ожидание элемента на странице в течении некоторого времени
     *
     * @param elementName    наименование элемента
     * @param timeoutSeconds количество секунд
     */
 //   @Когда("подождать появления элемента {string} в течение {int} секунд")
    public void waitUntilElementIsVisibleOnPage(String elementName, int timeoutSeconds) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.elementVisibleOnPage(element, timeoutSeconds);
        LOGGER.info("на странице '{}' имеется элемент '{}'", pageManager.getCurrentPage().name(), elementName);
    }

//    @Когда("элемент {string} не активен")
    public void checkActive(String elementName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        element.shouldNotBe(Condition.enabled);
        LOGGER.info("элемент {} не активен ", elementName);
    }

 //   @Когда("элемент {string} активен из-за присутствия параметра {string}")
    public void checkElementIsNotReadOnly(String elementName, String text) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.elementIsOnPage(element, text);
        LOGGER.info("элемент {} не активен из-за присутствия параметра {}", elementName, text);
    }

//    @Тогда("проверить поле {string}, что текст в поле {string}")
    public void matchText(String elementName, String text) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.elementTextEqualsExpectedText(element, text);
        LOGGER.info("на странице '{}' имеется элемент '{}'", pageManager.getCurrentPage().name(), elementName);
    }

//    @И("элемент {string} присутствует на странице")
//    @И("поле {string} присутствует на странице")
 //   @И("кнопка {string} присутствует на странице")
 //   @Если("в поле The Сотрудник “ и ” was changed successfully. присутствует элемент {string}")
 //   @Если("в полях The Сотрудник “ и ” was changed successfully. You may edit it again below. присутствует элемент {string}")
    public void curFieldsContainsThatElement(String elementName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.elementVisibleOnPage(element, null);
        LOGGER.info("в поле имеется элемент '{}'", elementName);
    }

//    @Если("поле {string} содержит значение {string}")
    public void matchsText(String elementName, String text) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.elementTextEqualsExpectedText(element, text);
        LOGGER.info("в поле '{}' содержится значение '{}'", elementName, text);
    }

 //   @Если("на странице в блоке {string} выбрать элемент {string}")
    public void elementIsOnPage(String elementN, String elementName) {
        pageManager.getCurrentPage().getElement(elementN);
        SelenideElement elementTwo = pageManager
                .getCurrentPage()
                .getElement(elementName);
        elementTwo.click();
        LOGGER.info("на текущей странице в блоке '{}' нажимается элемент '{}'", pageManager.getCurrentPage().name(), elementName);
    }

//    @Если("в текущем поле {string} отсутствует текст")
    public void checkFieldWithoutText(String elementName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.emptyElement(element);
        LOGGER.info("элемент '{}' не содержит текст", elementName);
    }

//    @Если("в текущем блоке поле {string} отсутствует")
    public void checkElementNotVisible(String elementName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.elementNotVisibleOnPage(element, null);
        LOGGER.info("элемент '{}' не отображается на странице", elementName);
    }
}

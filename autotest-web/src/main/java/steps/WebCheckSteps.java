package steps;

import actions.Checks;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.lanit.at.web.pagecontext.PageManager;

public class WebCheckSteps {

    private PageManager pageManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(WebCheckSteps.class);

    public WebCheckSteps(PageManager pageManager) {
        this.pageManager = pageManager;
    }

    /**
     * проверка присутствия текста на странице
     *
     * @param text текст
     */
    @Когда("на странице присутствует текст {string}")
    public void textAppearOnThePage(String text) {
        Checks.textVisibleOnPage(text, null);
        LOGGER.info("на странице '{}' имеется текст '{}'", pageManager.getCurrentPage().name(), text);
    }

    /**
     * ожидание появления текста на странице в течении некоторого времени
     *
     * @param text           текст
     * @param timeoutSeconds количество секунд
     */
    @Когда("подождать появления текста {string} в течение {int} секунд")
    public void waitUntilTextAppearOnPage(String text, int timeoutSeconds) {
        Checks.textVisibleOnPage(text, timeoutSeconds);
        LOGGER.info("на странице '{}' имеется текст '{}'", pageManager.getCurrentPage().name(), text);
    }

    /**
     * проверка что элемент заблокирован
     *
     * @param elementName название элемента
     */
    @Когда("элемент {string} заблокирован")
    public void checkElementIsReadOnly(String elementName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.elementIsReadOnly(element, "readonly", null);
        LOGGER.info("элемент {} заблокирован", elementName);
    }

    /**
     * проверка отсутствия текста на странице
     *
     * @param elementName   название элемента
     * @param attributeName название атрибута
     * @param value         значение атрибута
     * @param sec           ожидание сек.
     */
    @И("проверить что {string} есть  атрибут {string} с значением {string} подождав {int} сек.")
    public void elementAttributeValue(String elementName, String attributeName, String value, int sec) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.checkAttribute(element, attributeName, value, sec);
        LOGGER.info("у элемента '{}' имеется атрибут '{}' со значением '{}' ", elementName, attributeName, value);
    }

    /**
     * ожидание исчезновения текста на странице в течении некоторого времени
     *
     * @param text           текст
     * @param timeoutSeconds количество секунд
     */
    @Когда("подождать исчезновения текста {string} в течение {int} секунд")
    public void waitUntilTextAbsentOnPage(String text, int timeoutSeconds) {
        Checks.textAbsentOnPage(text, timeoutSeconds);
        LOGGER.info("на странице '{}' отсутствует текст '{}'", pageManager.getCurrentPage().name(), text);
    }

    /**
     * ожидание элемента на странице в течении некоторого времени
     *
     * @param elementName    наименование элемента
     * @param timeoutSeconds количество секунд
     */
    @Когда("подождать появления элемента {string} в течение {int} секунд")
    public void waitUntilElementIsVisibleOnPage(String elementName, int timeoutSeconds) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.elementVisibleOnPage(element, timeoutSeconds);
        LOGGER.info("на странице '{}' имеется элемент '{}'", pageManager.getCurrentPage().name(), elementName);
    }

    /**
     * проверка что на странице отсуствует элемент
     *
     * @param elementName наименование элемента
     */
    @Когда("на странице отсутствует элемент {string}")
    public void elementAbsentOnPage(String elementName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.elementAbsentOnPage(element, null);
        LOGGER.info("на странице '{}' отсутствует элемент '{}'", pageManager.getCurrentPage().name(), elementName);
    }

    /**
     * проверка, что поле заполнено текстом
     * <br/>можно начать написание url с переменной %{apiUrl}% или %{webUrl}%
     */
    @Если("поле {string} заполнено текстом {string}")
    public void checkFieldText(String elementName, String text) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.elementValueEqualsExpectedText(element, text, 0);
        LOGGER.info("на странице '{}' отсутствует элемент '{}'", pageManager.getCurrentPage().name(), elementName);
    }

    @Если("в выпадющем списке {string} выбран элемент со значением {string}")
    public void listCheckedElement(String elementName, String text) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.elementTextContainsExpectedText(element, text, null);
        LOGGER.info("в выпадющем списке {} выбран элемент со значением {}", elementName, text);
    }

    @Когда("элемент {string} не активен из-за отсутствия параметра {string}")
    public void checkReadOnly(String elementName, String text) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.elementIsNotOnPage(element, text);
        LOGGER.info("элемент {} не активен из-за отсутствия параметра {}", elementName, text);
    }

    @Когда("элемент {string} не активен")
    public void checkActive(String elementName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        element.shouldNotBe(Condition.enabled);
        LOGGER.info("элемент {} не активен ", elementName);
    }

    @Когда("элемент {string} активен из-за присутствия параметра {string}")
    public void checkElementIsNotReadOnly(String elementName, String text) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.elementIsOnPage(element, text);
        LOGGER.info("элемент {} не активен из-за присутствия параметра {}", elementName, text);
    }

    @Тогда("проверить поле {string}, что текст в поле {string}")
    public void matchText(String elementName, String text) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.elementTextEqualsExpectedText(element, text);
        LOGGER.info("на странице '{}' имеется элемент '{}'", pageManager.getCurrentPage().name(), elementName);
    }

    @Когда("элемент {string} активен")
    public void checkElementIsRead(String elementName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.elementIsOn(element);
        LOGGER.info("элемент {} активен", elementName);
    }
}

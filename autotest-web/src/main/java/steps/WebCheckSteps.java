package steps;

import actions.WebChecks;
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
        WebChecks.textVisibleOnPage(text, null);
        LOGGER.info("на странице '{}' имеется текст '{}'", pageManager.getCurrentPage().name(), text);
    }

    /**
     * проверка что элемент заблокирован
     *
     * @param elementName название элемента
     *
     */
    @Когда("элемент {string} заблокирован")
    public void checkElementIsReadOnly(String elementName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        WebChecks.elementIsReadOnly(element,"readonly", null);
        LOGGER.info("элемент {} заблокирован", elementName);
    }

    /**
     * проверка отсутствия текста на странице
     *
     * @param elementName название элемента
     * @param attrName название атрибута
     * @param value значение атрибута
     * @param sec ожидание сек.
     *
     */
    @И("проверить что {string} есть  атрибут {string} с значением {string} подождав {int} сек.")
    public void elementAttributeValue(String elementName, String attrName, String value, int sec) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        WebChecks.checkAttribute(element, attrName, value, sec);
        LOGGER.info("у элемента '{}' имеется атрибут '{}' со значением '{}' ", elementName, attrName, value);
    }

    /**
     * ожидание появления текста на странице в течении некоторого времени
     *
     * @param text           текст
     * @param timeoutSeconds количество секунд
     */
    @Когда("подождать появления текста {string} в течение {int} секунд")
    public void waitUntilTextAppearOnPage(String text, int timeoutSeconds) {
        WebChecks.textVisibleOnPage(text, timeoutSeconds);
        LOGGER.info("на странице '{}' имеется текст '{}'", pageManager.getCurrentPage().name(), text);
    }

    /**
     * ожидание исчезновения текста на странице в течении некоторого времени
     *
     * @param text           текст
     * @param timeoutSeconds количество секунд
     */
    @Когда("подождать исчезновения текста {string} в течение {int} секунд")
    public void waitUntilTextAbsentOnPage(String text, int timeoutSeconds) {
        WebChecks.textAbsentOnPage(text, timeoutSeconds);
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
        WebChecks.elementVisibleOnPage(element, timeoutSeconds);
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
        WebChecks.elementAbsentOnPage(element, null);
        LOGGER.info("на странице '{}' отсутствует элемент '{}'", pageManager.getCurrentPage().name(), elementName);
    }

    /**
     * проверка текущего url
     * <br/>можно начать написание url с переменной %{apiUrl}% или %{webUrl}%
     *
     * @param url часть или полный url (также может содержать переменные)
     */
    @Тогда("проверить что текущий url соответствует {string}")
    public void currentUrlEqualsExpected(String url) {
        WebChecks.urlEquals(url);
    }

    /**
     * проверка текущего url
     * <br/>можно начать написание url с переменной %{apiUrl}% или %{webUrl}%
     *
     * @param url часть url (также может содержать переменные)
     */
    @Тогда("проверить что текущий url содержит текст {string}")
    public void currentUrlContainsExpected(String url) {
        WebChecks.urlContains(url);
    }

    /**
     * проверка, что поле заполнено текстом
     * <br/>можно начать написание url с переменной %{apiUrl}% или %{webUrl}%
     *
     */
    @Если("поле {string} заполнено текстом {string}")
    public void checkFieldText(String elementName,String text){
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        WebChecks.elementValueEqualsExpectedText(element, text, 0);
        LOGGER.info("на странице '{}' отсутствует элемент '{}'", pageManager.getCurrentPage().name(), elementName);
    }
    @Если("в выпадющем списке {string} выбран элемент со значением {string}")
    public void listCheckedElement(String elementName,String text){
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        WebChecks.elementTextContainsExpectedText(element,text,null);
        LOGGER.info("в выпадющем списке {} выбран элемент со значением {}", elementName, text);
    }

    @Когда("элемент {string} не активен")
    public void checkReadOnly(String elementName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        WebChecks.elementReadOnly(element);
        LOGGER.info("элемент {} не активен", elementName);
    }

    /**
     * проверка что элемент заблокирован
     *
     * @param elementName название элемента
     *
     */
    @Когда("элемент {string} разблокирован")
    public void checkElementIsNotReadOnly(String elementName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        WebChecks.elementIsNotReadOnly(element);
        LOGGER.info("элемент {} разблокирован", elementName);
    }

}

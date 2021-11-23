package steps;

import actions.WebActions;
import actions.WebChecks;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.restassured.path.json.JsonPath;
import net.minidev.json.JSONObject;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.lanit.at.api.testcontext.ContextHolder;
import ru.lanit.at.utils.DataGenerator;
import ru.lanit.at.web.pagecontext.PageManager;
import ru.lanit.at.utils.Sleep;

import java.util.Random;

import static com.codeborne.selenide.Selenide.$;
import static io.restassured.RestAssured.given;


public class WebActionSteps {

    private static final Logger LOG = LoggerFactory.getLogger(WebActionSteps.class);
    private PageManager pageManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(WebActionSteps.class);

    public WebActionSteps(PageManager manager) {
        this.pageManager = manager;
    }

    /**
     * нажимает на элемент по тексту
     *
     * @param text текст элемента
     */
    @Когда("кликнуть на элемент по тексту {string}")
    public void clickElementWithText(String text) {
        $(Selectors.byText(text))
                .shouldBe(Condition.visible)
                .click();
        LOGGER.info("клик на элемент по тексту '{}'", text);
    }

    @Если("кликнуть на элемент {string}")
    public void clickOnElement(String elementName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        element
                .shouldBe(Condition.visible)
                .click();
        LOGGER.info("клик на элемент '{}'", elementName);
    }

    /**
     * скролл до элемента
     *
     * @param elementName наименование элемента
     */
    @Когда("проскроллить страницу до элемента {string}")
    public void scrollToElement(String elementName) {
        SelenideElement element = pageManager.getCurrentPage().getElement(elementName);
        element.shouldBe(Condition.visible)
                .scrollIntoView("{block: 'center'}");
        LOGGER.info("скролл страницы до элемента '{}'", elementName);
    }

    /**
     * скролл до текста
     *
     * @param text текст
     */
    @Когда("проскроллить страницу до текста {string}")
    public void scrollToText(String text) {
        SelenideElement element = $(Selectors.byText(text));
        element.shouldBe(Condition.visible)
                .scrollIntoView("{block: 'center'}");
        LOGGER.info("скролл страницы до текста '{}'", text);
    }

    @И("подождать {int} сек")
    public void waitSeconds(int timeout) {
        Sleep.pauseSec(timeout);
    }

    /**
     * Ввод значения в элемент
     *
     * @param field - наименование элемента
     * @param value - значение
     */
    @Когда("ввести в поле {string} значение {string}")
    public void fillTheField(String field, String value) {
        SelenideElement fieldElement = pageManager
                .getCurrentPage()
                .getElement(field);
        fieldElement
                .shouldBe(Condition.visible)
                .setValue(value);
        LOGGER.info("в поле '{}' введено значение '{}'", field, value);
    }

    /**
     * Ввод значения в элемент
     *
     * @param field - наименование элемента
     * @param mask - значение
     */
    @Когда("ввести в поле {string} случайное значение по маске {string}")
    public void generateFillTheField(String field, String mask) {
        SelenideElement fieldElement = pageManager
                .getCurrentPage()
                .getElement(field);
        String value = DataGenerator.generateValueByMask(mask);
        fieldElement
                .shouldBe(Condition.visible)
                .setValue(value);
        LOGGER.info("ввести в поле {} случайное значение {}", field, value);
    }

    /**
     * Очистка поля
     *
     * @param elementName наименование элемента
     */
    @Если("очистить поле {string}")
    public void clearFiled(String elementName) {
        pageManager
                .getCurrentPage()
                .getElement(elementName)
                .shouldBe(Condition.visible)
                .clear();
    }

    @Если("в выпадющем списке {string} выбрать элемент со значением {string}")
    public void listSelectElement(String elementName,String text){
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
                element.selectOptionContainingText("Female");
        LOGGER.info("в выпадющем списке {} выбран элемент со значением {}", elementName, text);
    }
    @Если("в выпадющем списке {string} выбрать случайный элемент")
    public void listSelectRandElement(String elementName){
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        element.selectOption( 1 + (int) (Math.random() * element.findAll(By.cssSelector("option")).size()-1));
        LOGGER.info("в выпадющем списке {} выбран элемент со значением", elementName);
    }
}

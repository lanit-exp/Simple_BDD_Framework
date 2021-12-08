package steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.DownloadOptions;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Затем;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.lanit.at.utils.DataGenerator;
import ru.lanit.at.web.pagecontext.PageManager;

import java.io.File;
import java.io.FileNotFoundException;

import static com.codeborne.selenide.FileDownloadMode.FOLDER;
import static com.codeborne.selenide.files.FileFilters.withExtension;

public class WebActionSteps {

    private PageManager pageManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(WebActionSteps.class);

    public WebActionSteps(PageManager manager) {
        this.pageManager = manager;
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
    public void listSelectElement(String elementName, String text){
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
                element.selectOptionContainingText(text);
        LOGGER.info("в выпадющем списке {} выбран элемент со значением {}", elementName, text);
    }

    @Если("в выпадющем списке {string} выбрать случайный элемент")
    public void listSelectRandElement(String elementName){
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        element.selectOption( 1 + (int) (Math.random() * element.findAll(By.cssSelector("option")).size()-1));
        String selectedText = element.getSelectedText();
        LOGGER.info("в выпадющем списке '{}' выбран элемент со случайным значением - '{}'", elementName, selectedText);
    }

    @Тогда("нажать на {string}")
    @Если("кликнуть на элемент {string}")
    @Тогда("нажать на кнопку {string}")
    public void clickOnButton(String elementName) {
        pageManager
                .getCurrentPage()
                .getElement(elementName)
                .shouldBe(Condition.visible)
                .click();
        LOGGER.info("клик на элемент '{}'", elementName);
    }

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

    @Затем("на текущей странице в поле {string} загрузить файл {string}")
    public void uploadFiles(String elementName, String path) {
        SelenideElement uploadElement = pageManager
                .getCurrentPage()
                .getElement(elementName);

        uploadElement.uploadFile(new File("src/test/resources/files/" + path));

        LOGGER.info("на странице '{}' загружено изображение '{}'", pageManager.getCurrentPage().name(), elementName);
    }

    @Затем("на текущей странице нажать на кнопку с текстом {string}, проверить, что скачивается файл с раширением {string}")
    public void downloadFiles(String elementName, String fileType) throws FileNotFoundException  {

        SelenideElement downloadElement = pageManager
                .getCurrentPage()
                .getElement(elementName)
                .shouldBe(Condition.visible);

        File file = downloadElement.download(DownloadOptions.using(FOLDER).withFilter(withExtension(fileType)).withTimeout(1000));

        file.delete();

        LOGGER.info("на странице '{}' скачано изображение '{}'", pageManager.getCurrentPage().name(), elementName);
    }

    @Если("закрыть страницу")
    public void closeDriver() {
        WebDriverRunner.getWebDriver().close();
    }
}

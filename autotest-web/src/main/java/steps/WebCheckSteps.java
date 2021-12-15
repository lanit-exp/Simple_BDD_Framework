package steps;

import actions.Checks;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.DownloadOptions;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.ru.*;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import ru.lanit.at.api.testcontext.ContextHolder;
import ru.lanit.at.web.pagecontext.PageManager;

import java.io.File;
import java.io.FileNotFoundException;

import static com.codeborne.selenide.FileDownloadMode.FOLDER;
import static com.codeborne.selenide.files.FileFilters.withExtension;

public class WebCheckSteps {

    private PageManager pageManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(WebCheckSteps.class);

    public WebCheckSteps(PageManager pageManager) {
        this.pageManager = pageManager;
    }

    /**
     * проверка присутствия и активности чекбокса на странице
     *
     * @param elementName = название чекбокса
     */
    @Step("чекбокс {elementName} отображается и не выбран")
    @Пусть("чекбокс {string} отображается и не выбран")
    public void checkNoSelectedCheckbox(String elementName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.elementVisibleAndNoSelected(element);
        LOGGER.info("на странице '{}' имеется и не выбран элемент '{}'", pageManager.getCurrentPage().name(), elementName);
    }

    /**
     * проверка присутствия и неактивности чекбокса на странице
     *
     * @param elementName = название чекбокса
     */
    @Step("чекбокс {elementName} отображается и выбран")
    @Пусть("чекбокс {string} отображается и выбран")
    public void checkSelectedCheckbox(String elementName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.elementVisibleAndSelected(element);
        LOGGER.info("на странице '{}' имеется и выбран элемент '{}'", pageManager.getCurrentPage().name(), elementName);
    }

    /**
     * проверка отсутствия текста или поля на странице
     *
     * @param text = текст или название поля
     */
    @Step("проверка отсутствия текста {text}")
    @Если("на странице отсутствует поле под названием {string}")
    @Если("на странице отсутствует текст {string}")
    public void currentTextIsNotExist(String text) {
        Checks.textAbsentOnPage(text, null);
        LOGGER.info("на странице отсутствует текст '{}'", text);
    }

    /**
     * проверка присутствия текста на странице
     *
     * @param text = текст
     */
    @Step("проверка присутствия текста {text}")
    @Когда("на странице присутствует текст {string}")
    @Если("на странице в поле Добро пожаловать, имеется элемент {string}")
    public void textAppearOnThePage(String text) {
        Checks.textVisibleOnPage(text, null);
        LOGGER.info("на странице '{}' имеется текст '{}'", pageManager.getCurrentPage().name(), text);
    }

    /**
     * проверка присутствия элемента на странице
     *
     * @param elementName = название элемента
     */
    @Step("проверка присутствия элемента {elementName} на странице")
    @Если("на странице имеется элемент {string}")
    @Пусть("{string} отображается")
    @Если("поле {string} отображается")
    @Если("кнопка {string} отображается")
    @Пусть("поле ввода {string} отображается")
    public void checkAppearElement(String elementName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.elementVisibleOnPage(element, null);
        LOGGER.info("на странице '{}' имеется элемент '{}'", pageManager.getCurrentPage().name(), elementName);
    }

    @Step("в текущем блоке поле {elementName} отсутствует")
    @И("в текущем блоке поле {string} отсутствует")
    public void checkElementNotVisible(String elementName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.elementNotVisibleOnPage(element, null);
        LOGGER.info("элемент '{}' не отображается на странице", elementName);
    }

    /**
     * проверка, что на странице отсуствует элемент
     *
     * @param elementName = наименование элемента
     */
    @Step("проверка отсутствия элемента {elementName}")
    @Когда("на странице отсутствует элемент {string}")
    public void elementAbsentOnPage(String elementName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.elementAbsentOnPage(element, null);
        LOGGER.info("на странице '{}' отсутствует элемент '{}'", pageManager.getCurrentPage().name(), elementName);
    }

    /**
     * проверка что элемент заблокирован
     *
     * @param elementName = название элемента
     */
    @Step("проверка, что элемент {elementName} заблокирован")
    @Когда("элемент {string} заблокирован")
    public void checkElementIsReadOnly(String elementName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.elementIsReadOnly(element, "readonly", null);
        LOGGER.info("элемент {} заблокирован", elementName);
    }

    /**
     * проверка присутствия нужного атрибута со значением
     *
     * @param elementName   название элемента
     * @param attributeName название атрибута
     * @param value         значение атрибута
     * @param sec           ожидание сек.
     */
    @Step("проверка того, что {elementName} - это атрибут {attributeName} со значением {value}, подождав {sec} сек.")
    @И("проверить что {string} есть  атрибут {string} с значением {string} подождав {int} сек.")
    public void elementAttributeValue(String elementName, String attributeName, String value, int sec) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.checkAttribute(element, attributeName, value, sec);
        LOGGER.info("у элемента '{}' имеется атрибут '{}' со значением '{}' ", elementName, attributeName, value);
    }

    /**
     * проверка того, что поле заполнено текстом
     *
     * @param elementName название поля
     * @param text        текст
     */
    @Step("проверка того, что поле {elementName} заполнено текстом {text}")
    @Если("поле {string} заполнено текстом {string}")
    public void checkFieldText(String elementName, String text) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.elementValueEqualsExpectedText(element, text, 0);
        LOGGER.info("поле '{}' заполнено значением '{}'", pageManager.getCurrentPage().name(), elementName);
    }

    /**
     * проверка того, что поле отображается и не заполнено
     *
     * @param elementName название поля
     */
    @Step("проверка, что поле {elementName} отображается и не заполнено")
    @Если("поле {string} отображается и пусто")
    public void checkEmptyField(String elementName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.fieldVisibleAndNoSelected(element);
        LOGGER.info("в блоке есть пустое поле '{}'", elementName);
    }

    /**
     * проверка того, что в выпадающем списке выбран элемент со значением
     *
     * @param elementName название выпадающего списка
     * @param text        значение
     */
    @Step("проверка того, что в выпадющем списке {elementName} выбран элемент со значением {text}")
    @Если("в выпадющем списке {string} выбран элемент со значением {string}")
    public void listCheckedElement(String elementName, String text) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.elementTextContainsExpectedText(element, text, null);
        LOGGER.info("в выпадющем списке {} выбран элемент со значением {}", elementName, text);
    }

    /**
     * проверка, что элемент не активен из-за отсутствия параметра
     *
     * @param elementName название элемента
     * @param text        параметр
     */
    @Step("проверка того, что элемент {elementName} не активен из-за отсутствия параметра {text}")
    @Когда("элемент {string} не активен из-за отсутствия параметра {string}")
    public void checkReadOnly(String elementName, String text) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.elementIsNotOnPage(element, text);
        LOGGER.info("элемент {} не активен из-за отсутствия параметра {}", elementName, text);
    }

    /**
     * проверка, что элемент активен
     *
     * @param elementName название элемента
     */
    @Step("проверка того, что элемент {elementName} активен")
    @Когда("элемент {string} активен")
    public void checkElementIsRead(String elementName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.elementIsOn(element);
        LOGGER.info("элемент {} активен", elementName);
    }

    /**
     * проверка скачивания файла с раширением после нажатия на кнопку
     *
     * @param elementName название кнопки
     * @param fileType    расширение
     */
    @Step("на текущей странице нажимается кнопка с текстом {elementName}, проверка скачивания файла с раширением {fileType}")
    @Затем("на текущей странице нажать на кнопку с текстом {string}, проверить, что скачивается файл с раширением {string}")
    public void downloadFiles(String elementName, String fileType) throws FileNotFoundException {

        SelenideElement downloadElement = pageManager
                .getCurrentPage()
                .getElement(elementName)
                .shouldBe(Condition.visible);

        File file = downloadElement.download(DownloadOptions.using(FOLDER).withFilter(withExtension(fileType)).withTimeout(1000));

        file.delete();

        LOGGER.info("на странице '{}' скачано изображение '{}'", pageManager.getCurrentPage().name(), elementName);
    }

    /**
     * проверка того, что в выпадающем списке не выбрано поле
     *
     * @param elementName название поля
     */
    @Step("проверка того, что в выпадающем списке поле {elementName} не выбрано")
    @Если("в выпадающем списке поле {string} не выбрано")
    public void emptyDropDown(String elementName) {
        SelenideElement elements = pageManager
                .getCurrentPage()
                .getElement(elementName);
        elements.shouldBe(Condition.visible).selectOption(0);
        LOGGER.info("на странице '{}' не выбран элемент '{}'", pageManager.getCurrentPage().name(), elementName);
    }

    /**
     * проверка того, что запись в блоке изменилась
     *
     * @param index       номер записи
     * @param elementName название блока
     */
    @Step("проверка того, что {index} запись в блоке {elementName} изменилась")
    @Тогда("{int} запись в блоке {string} изменилась")
    public void checkElementNoEquals(int index, String elementName) {
        ElementsCollection elements = pageManager
                .getCurrentPage()
                .getElementsCollection(elementName);
        ContextHolder.put("fio2", elements.get(index - 1).getText());

        Assert.assertNotEquals(ContextHolder.getValue("fio1").toString(), ContextHolder.getValue("fio2").toString());
        LOGGER.info("на странице '{}' в блоке '{}' запись '{}' изменился на '{}'", pageManager.getCurrentPage().name(), elementName, ContextHolder.getValue("fio1").toString(), ContextHolder.getValue("fio2").toString());
    }

    /**
     * проверка того, что при нажатии на кнопку в блоке 'Таюлица' в столбце некий элемент не изменился
     *
     * @param index       номер записи
     * @param elementName название блока
     */
    @Step("проверка того, что нажатие на кнопку {buttonName} в блоке 'Таблица' в столбце {elementName}, {index} элемент не изменился")
    @Если("при нажатии на кнопку {string} в блоке 'Таблица' в столбце {string}, {int} элемент не изменился")
    public void checkElementEquals(String buttonName, String elementName, int index) {
        SelenideElement button = pageManager
                .getCurrentPage()
                .getElement(buttonName);
        button.click();
        ElementsCollection elements = pageManager
                .getCurrentPage()
                .getElementsCollection(elementName);
        ContextHolder.put("fio2", elements.get(index - 1).getText());

        Assert.assertEquals(ContextHolder.getValue("fio1").toString(), ContextHolder.getValue("fio2").toString());
        LOGGER.info("на странице '{}' в блоке '{}' запись '{}' осталась '{}'", pageManager.getCurrentPage().name(), elementName, ContextHolder.getValue("fio1").toString(), ContextHolder.getValue("fio2").toString());
    }

    /**
     * проверка того, что все элементы таблицы содержат текст
     *
     * @param element название таблицы
     * @param text    текст
     */
    @Step("проверка того, что в таблице все элементы {element} содержат текст {text}")
    @Если("в таблице все элементы {string} содержат текст {string}")
    public void elementContainsText(String element, String text) {
        ElementsCollection elements = pageManager
                .getCurrentPage()
                .getElementsCollection(element);
        for (SelenideElement selenideElement : elements) {
            selenideElement.shouldBe(Condition.matchText(text));
        }
        LOGGER.info("на текущей странице в элементах '{}' присутствует текст '{}'", pageManager.getCurrentPage().name(), text);
    }

    /**
     * проверка того, что в блоке содержится некоторое количество записей
     *
     * @param elementName название блока
     * @param number      количество записей
     */
    @Step("проверка того, что в блоке {elementName} количество записей равно {number}")
    @Если("в блоке {string} количество записей равно {int}")
    public void matchRecordsNumber(String elementName, int number) {
        ElementsCollection elements = pageManager
                .getCurrentPage()
                .getElementsCollection(elementName);
        Assert.assertEquals(elements.size(), number);
        LOGGER.info("на странице '{}' в блоке '{}' количество записей '{}'", pageManager.getCurrentPage().name(), elementName, number);
    }

    /**
     * проверка того, что в блоке 'Таблица' в столбце все записи содержат город
     *
     * @param elementName название столбца
     * @param city        название города
     */
    @Step("проверка того, что в блоке 'Таблица' в столбце {elementName} все записи = {city}")
    @Если("в блоке 'Таблица' в столбце {string} все записи = {string}")
    public void checkCurrentNumberCity(String elementName, String city) {
        ElementsCollection elements = pageManager
                .getCurrentPage()
                .getElementsCollection(elementName);
        boolean result = true;
        for (SelenideElement element : elements) {
            if (!element.getText().equals(city)) {
                result = false;
                break;
            }
        }
        Assert.assertTrue(result);
        LOGGER.info("на странице '{}' в блоке '{}' текущий город '{}': '{}'", pageManager.getCurrentPage().name(), elementName, city, result);
    }

    /**
     * проверка того, что в элементе активен номер
     *
     * @param elementName название элемента
     * @param number      номер
     */
    @Step("в {elementName} активный номер {number}")
    @Если("в {string} активный номер {string}")
    public void checkCurrentNumber(String elementName, String number) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.elementTextEqualsExpectedText(element, number);
        LOGGER.info("на странице '{}' в блоке '{}' текущий номер '{}'", pageManager.getCurrentPage().name(), elementName, number);
    }

    /**
     * проверка того, что на странице присутствует элемент с заданым текстом
     *
     * @param elementName название элемента
     * @param text        текст
     */
    @Step("проверка того, что на странице присутствует элемент {elementName} с текстом {text}")
    @Если("на текущей странице {string} с текстом {string} присутствует")
    @И("поле {string} инфоблок с текстом {string} присутствует")
    public void matchText(String elementName, String text) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.elementTextEqualsExpectedText(element, text);
        LOGGER.info("на странице '{}' имеется элемент '{}', с текстом '{}'", pageManager.getCurrentPage().name(), elementName, text);
    }

    @И("на странице присутствует {string} {string}")
    public void checkElementVisibleCollection(String elementName, String text) {
        ElementsCollection elements = pageManager
                .getCurrentPage()
                .getElementsCollection(elementName);
        boolean result = false;
        for (SelenideElement element : elements) {
            if (element.getText().equals(text)) {
                result = true;
                break;
            }
        }
        Assert.assertTrue(result);
        LOGGER.info("на странице '{}' в блоке '{}' есть '{}'", pageManager.getCurrentPage().name(), elementName, text);
    }

    /**
     * Сравнение текста выделенного поля и содержимого ContextHolder
     *
     * @param fieldName - название поля
     */
    @Step("сравнение текста выделенного поля {fieldName} и содержимое ContextHolder")
    @И("сравнить текст выделенного поля {string} и содержимое ContextHolder")
    public void checkSelectedField(String fieldName) {
        String expectedValue = ContextHolder.getValue(fieldName).toString();
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(fieldName);
        String actualValue = element.getSelectedText();
        Assert.assertEquals(actualValue, expectedValue);
        LOGGER.info("Содержимое поля - {}", actualValue);
    }

    /**
     * Сохранение значения выпадающего списка в ContextHolder
     *
     * @param fieldName - название списка
     */
    @Step("сравнение значения поля {fieldName} и содержимого ContextHolder")
    @И("сравнить значение поля {string} и содержимое ContextHolder")
    public void checkField(String fieldName) {
        String expectedValue = ContextHolder.getValue(fieldName).toString();
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(fieldName);
        String actualValue = element.getValue();
        Assert.assertEquals(actualValue, expectedValue);
        LOGGER.info("Ожидаемое значение поля: '{}', актуальное значения поля: {}", expectedValue, actualValue);
    }

    @Step("в текущем блоке поле {elementName} отсутствует текст")
    @И("в текущем поле {string} отсутствует текст")
    public void checkFieldWithoutText(String elementName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        Checks.emptyElement(element);
        LOGGER.info("элемент '{}' не содержит текст", elementName);
    }


}

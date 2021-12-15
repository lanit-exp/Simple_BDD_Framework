package ts5;

import hooks.WebHooks;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import ru.lanit.at.web.pagecontext.PageManager;
import steps.*;

public class EmployeePageTest extends WebHooks {

    private PageManager pageManager = new PageManager();
    private final WebSteps webSteps = new WebSteps(pageManager);
    private final WebCheckSteps webCheckSteps = new WebCheckSteps(pageManager);

    private void initialize() {
        webSteps.openUrl();
        webSteps.setPage("DjangoAuthorization");
        webSteps.authWithLogin("hr");
        webSteps.setPage("DjangoAdministration");
        webSteps.clickOnElement("Сотрудники");
    }

    @Test
    @Description("5.6 страница Сотрудники, проверка работотоспособности кнопок пагинации. Роль Hr")
    public void employeeHrPaginationTest() {
        initialize();
        webSteps.setPage("DjangoEmployee");
        webSteps.getElementText("ФИО", 1);
        webSteps.clickOnElementWithText("Пагинация", "2");
        webCheckSteps.checkCurrentNumber("Пагинация текущая страница", "2");
        webCheckSteps.checkElementNoEquals(1, "ФИО");
    }

    @Test
    @Description("5.7 страница Сотрудники, проверка работотоспособности восстановления удаленных сотрудников. Роль Hr")
    public void employeeHrRestoreTest() {
        initialize();
        webSteps.setPage("DjangoEmployee");
        webSteps.getElementText("ФИО", 1);
        webSteps.clickOnElementWithText("Пагинация", "2");
        webCheckSteps.checkCurrentNumber("Пагинация текущая страница", "2");
        webCheckSteps.checkElementNoEquals(1, "ФИО");
    }

    @Test
    @Description("5.9 страница Сотрудники, проверка работотоспособности экспорта списка сотрудников через кнопку интерфейса. Роль Hr")
    public void employeeHrExportTest() {
        initialize();
        webSteps.setPage("DjangoEmployeeChange");
        webCheckSteps.checkAppearElement("Экспорт");
        webSteps.clickOnElement("Экспорт");
        webSteps.setPage("DjangoExportEmployee");
        webCheckSteps.matchText("Инфоблок", "Экспорт");
        webCheckSteps.checkAppearElement("Формат");
        webCheckSteps.checkElementVisibleCollection("Чекбокс", "Договор");
        webCheckSteps.checkAppearElement("Отправить");
    }

    @Test
    @Description("5.10 страница Сотрудники, проверка работотоспособности кнопки добавления нового сотрудника. Роль Hr")
    public void employeeHrAddNewEmployeeTest() {
        initialize();
        webSteps.setPage("DjangoEmployeeChange");
        webCheckSteps.checkAppearElement("Добавить сотрудник");
        webSteps.clickOnElement("Добавить сотрудник");
        webSteps.setPage("DjangoExportEmployee");
        webCheckSteps.matchText("Инфоблок", "Добавить Сотрудник");
    }

    @Test
    @Description("5.11 страница Сотрудники, проверка работотоспособности фильтров. Роль Hr")
    public void employeeHrFiltersTest() {
        initialize();
        webSteps.setPage("DjangoEmployeeChange");
        webSteps.clickOnElementWithText("Категории фильтров", "Офис");
        webSteps.clickOnElementWithText("Фильтр", "Пенза");
        webCheckSteps.checkCurrentNumberCity("Текущий город", "Пенза");
    }
}

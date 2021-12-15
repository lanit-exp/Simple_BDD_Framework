package ts7;

import hooks.WebHooks;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import ru.lanit.at.web.pagecontext.PageManager;
import steps.*;

public class AddEmployeePageTest extends WebHooks {

    private PageManager pageManager = new PageManager();
    private final WebSteps webSteps = new WebSteps(pageManager);
    private final WebCheckSteps webCheckSteps = new WebCheckSteps(pageManager);

    private void initialize() {
        webSteps.openUrl();
        webSteps.setPage("DjangoAuthorization");
        webSteps.authWithLogin("hr");
        webSteps.setPage("DjangoAdministration");
        webSteps.clickOnElement("Сотрудники");
        webSteps.setPage("DjangoEmployee");
        webSteps.clickOnElement("Добавить сотрудник");
    }

    @Test
    @Description("7.31 страница Сотрудники, проверка блока 'Фактические отпуска'. Роль Hr")
    public void employeeHrSearchTest() {
        initialize();
        webSteps.setPage("DjangoEmployeeChange");
        webSteps.clickOnElement("Фактические отпуска");
        webCheckSteps.checkFieldWithoutText("Заметка поле");
        webSteps.clickOnElement("Скрыть отпуска");
        webCheckSteps.checkElementNotVisible("Заметка");
    }

    @Test
    @Description("7.32 страница Сотрудники, проверка блока 'Больничные'. Роль Hr")
    public void employeeHrSickLeaveTest() {
        initialize();
        webSteps.setPage("DjangoEmployeeChange");
        webSteps.clickOnElement("Больничные");
        webSteps.clickOnElement("Добавить больничный");
        webCheckSteps.checkFieldWithoutText("Номер больничного листа");
        webSteps.clickOnElement("Скрыть больничный");
        webCheckSteps.checkElementNotVisible("Номер больничного листа");
    }

    @Test
    @Description("7.33 страница Сотрудники, проверка блока 'Бюллетени без больничного листа'. Роль Hr")
    public void employeeHrBulletinWithoutLeaveTest() {
        initialize();
        webSteps.setPage("DjangoEmployeeChange");
        webSteps.clickOnElement("Бюллетени без больничного листа");
        webSteps.clickOnElement("Добавить бюллетень");
        webCheckSteps.checkFieldWithoutText("Дата бюллетеня");
        webSteps.clickOnElement("Скрыть бюллетень");
        webCheckSteps.checkElementNotVisible("Дата бюллетеня");
    }

    @Test
    @Description("7.34 страница Сотрудники, проверка блока 'Семья'. Роль Hr")
    public void employeeHrFamilyTest() {
        initialize();
        webSteps.setPage("DjangoEmployeeChange");
        webSteps.clickOnElement("Семья");
        webSteps.clickOnElement("Добавить семья");
        webCheckSteps.emptyDropDown("Родственная связь");
        webSteps.clickOnElement("Скрыть семья");
        webCheckSteps.checkElementNotVisible("Родственная связь");
    }
}

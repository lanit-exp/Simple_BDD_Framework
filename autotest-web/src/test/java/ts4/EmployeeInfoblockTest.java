package ts4;

import hooks.WebHooks;
import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.lanit.at.web.pagecontext.PageManager;
import steps.WebCheckSteps;
import steps.WebSteps;

public class EmployeeInfoblockTest extends WebHooks {

    private PageManager pageManager = new PageManager();
    private final WebSteps webSteps = new WebSteps(pageManager);
    private final WebCheckSteps webCheckSteps = new WebCheckSteps(pageManager);

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {"hr", "Сотрудники", "Заголовок таблицы", "Выберите Сотрудник для изменения"},
                {"hr", "Добавить поле Сотрудники", "Заголовок таблицы", "Добавить Сотрудник"},
                {"public", "Сотрудники", "Заголовок таблицы", "Выберите Сотрудник для просмотра"}
        };
    }

    @Test(dataProvider = "data")
    @Description("4.1-4.3 Проверка текста в инфоблоке, после нажатия на кнопку 'Сотрудники'. Роль Hr")
    public void employeeHrPublicInfoblockTest(String login, String link, String header, String text) {

        webSteps.openUrl();
        webSteps.setPage("DjangoAuthorization");
        webSteps.authWithLogin(login);
        webSteps.setPage("DjangoAdministration");
        webSteps.clickOnElement(link);
        webSteps.setPage("DjangoEmployeeChange");
        webCheckSteps.matchText(header, text);
    }
}

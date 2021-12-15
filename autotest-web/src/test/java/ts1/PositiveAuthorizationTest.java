package ts1;

import hooks.WebHooks;
import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.lanit.at.web.pagecontext.PageManager;
import steps.WebCheckSteps;
import steps.WebSteps;

public class PositiveAuthorizationTest extends WebHooks {

    private static PageManager pageManager = new PageManager();
    private static WebSteps webSteps = new WebSteps(pageManager);
    private static WebCheckSteps webCheck = new WebCheckSteps(pageManager);

    @DataProvider
    public Object[][] users() {
        return new Object[][] {
        {"DjangoAuthorization", "just_employee", "Сообщение об ошибке", "DjangoAdministration", "Выйти", "Войти снова"},
        {"DjangoAuthorization", "admin", "Сообщение об ошибке", "DjangoAdministration", "Выйти", "Войти снова"},
        {"DjangoAuthorization", "hr", "Сообщение об ошибке", "DjangoAdministration", "Выйти", "Войти снова"},
        {"DjangoAuthorization", "project1_admin", "Сообщение об ошибке", "DjangoAdministration", "Выйти", "Войти снова"}
        };
    }

    @Test(dataProvider = "users")
    @Description(value = "1.1-1.4 Авторизация под ролями just_employee, admin, hr, project1_admin")
    public static void checkAuthorization(String pageOne, String user, String message,
                                      String pageTwo, String buttonOne, String buttonTwo) {

        webSteps.openUrl();
        webSteps.setPage(pageOne);
        webSteps.authWithLogin(user);
        webCheck.currentTextIsNotExist(message);
        webSteps.setPage(pageTwo);
        webCheck.textAppearOnThePage(user);
        webSteps.clickOnElement(buttonOne);
        webSteps.clickOnElement(buttonTwo);
    }

    @Test
    @Description(value = "1.5 Авторизация под ролью public")
    public static void publicAuthor() {

        webSteps.openUrl();
        webSteps.setPage("DjangoAuthorization");
        webSteps.authWithLogin("public");
        webCheck.currentTextIsNotExist("Сообщение об ошибке");
        webSteps.setPage("DjangoAdministration");
        webCheck.textAppearOnThePage("employee995");
    }
}

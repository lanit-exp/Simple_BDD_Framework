import io.qameta.allure.Description;
import org.testng.annotations.Test;
import ru.lanit.at.web.pagecontext.PageManager;
import steps.*;

public class tk_8_6 {
    private PageManager pageManager = new PageManager();

    @Test
    @Description("Тест-кейс 8.6")
    public void test81(){
        AuthorizationSteps authorizationSteps = new AuthorizationSteps(pageManager);
        WebCheckSteps webCheckSteps = new WebCheckSteps(pageManager);
        WebActionSteps webActionSteps = new WebActionSteps(pageManager);
        EmployeeSteps employeeSteps = new EmployeeSteps(pageManager);
        EmployeeCheckSteps employeeCheckSteps = new EmployeeCheckSteps(pageManager);

        authorizationSteps.openUrl();// открываем сайт
        authorizationSteps.setPage("DjangoAuthorization"); // Инициализируем страницу
        authorizationSteps.fillField("логин", "just_employee");
        authorizationSteps.fillField("пароль", "hrmhrm123");
        authorizationSteps
                .clickSignInButton("войти");
        authorizationSteps.setPage("DjangoEmployeeChange");

        employeeSteps.scrollToElement("Квалификация");
        authorizationSteps
                .clickSignInButton("Квалификация");
        employeeSteps.clickRandomN("Все квалификации", 5);
        employeeSteps.waitSeconds(1);
        employeeSteps.unClickRandomN("Все квалификации", 3);
        employeeSteps.waitSeconds(1);
    }
}

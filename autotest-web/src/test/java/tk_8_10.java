import io.qameta.allure.Description;
import org.testng.annotations.Test;
import ru.lanit.at.web.pagecontext.PageManager;
import steps.AuthorizationSteps;
import steps.EmployeeCheckSteps;

public class tk_8_10 {
    private PageManager pageManager = new PageManager();

    @Test
    @Description("Тест-кейс 8.10")
    public void test82() {
        AuthorizationSteps authorizationSteps = new AuthorizationSteps(pageManager);
        EmployeeCheckSteps employeeCheckSteps = new EmployeeCheckSteps(pageManager);

        authorizationSteps.openUrl();
        authorizationSteps.setPage("DjangoAuthorization");

        authorizationSteps
                .clickSignInButton("Сохранить");

        employeeCheckSteps.matchText("Сообщение о успешном редактировании",
                "The Сотрудник “Бородкина Анастасия” was changed successfully.");
    }
}

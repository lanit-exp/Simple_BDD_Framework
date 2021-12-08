import hooks.WebHooks;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import ru.lanit.at.web.pagecontext.PageManager;
import steps.*;

public class tk_8_1 extends WebHooks {
    private PageManager pageManager = new PageManager();

    @Test
    @Description("Тест-кейс 8.1")
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

        webCheckSteps.checkFieldText("Фамилия", "Бородкин");
        webCheckSteps.checkFieldText("Имя", "Битард");
        webCheckSteps.listCheckedElement("Пол", "Male");
        webCheckSteps.checkElementIsReadOnly("Корпоративная почта");

        webActionSteps.fillTheField("Фамилия", "Бородкина");
        webActionSteps.fillTheField("Имя", "Анастасия");

        employeeSteps.setInDropDown("Пол", "Female");
        webActionSteps.clickOnButton("Сохранить и продолжить редактирование");


        employeeCheckSteps.matchText("Сообщение о успешном редактировании", "The Сотрудник “Бородкина Анастасия” was changed successfully. You may edit it again below.");

        webCheckSteps.checkFieldText("Фамилия", "Бородкина");
        webCheckSteps.checkFieldText("Имя", "Анастасия");
        webCheckSteps.listCheckedElement("Пол", "Female");
    }
}

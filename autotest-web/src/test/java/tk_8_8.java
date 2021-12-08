import io.qameta.allure.Description;
import org.testng.annotations.Test;
import ru.lanit.at.web.pagecontext.PageManager;
import steps.*;

public class tk_8_8 {
    private PageManager pageManager = new PageManager();

    @Test
    @Description("Тест-кейс 8.9")
    public void test81() {
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

        employeeSteps.scrollToElement("Сертификаты");
        authorizationSteps.clickSignInButton("Сертификаты");
        //        Допилить RRRRRRDDDDD
        webActionSteps.generateFillTheField("Сертификаты:", "RRRRRRDDDDD");
        webActionSteps.generateFillTheField("Курсы:", "RRRRRRDDDDD");
        authorizationSteps
                .clickSignInButton("Сохранить и продолжить редактирование");
        employeeCheckSteps.matchText("Сообщение о успешном редактировании",
                "The Сотрудник “Бородкина Анастасия” was changed successfully. You may edit it again below.");
    }
}

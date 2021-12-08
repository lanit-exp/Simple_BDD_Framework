import hooks.WebHooks;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.annotations.Test;
import ru.lanit.at.web.pagecontext.PageManager;
import steps.*;

public class tk_8_2 extends WebHooks {
    private PageManager pageManager = new PageManager();



    @Test
    @Step("Jojo")
    @Description("Тест-кейс 8.2")
    public void test82() {
        AuthorizationSteps authorizationSteps = new AuthorizationSteps(pageManager);
        WebActionSteps webActionSteps = new WebActionSteps(pageManager);
        EmployeeCheckSteps employeeCheckSteps = new EmployeeCheckSteps(pageManager);

        authorizationSteps.openUrl();
        authorizationSteps.setPage("DjangoAuthorization");

        authorizationSteps.fillField("логин", "just_employee");
        authorizationSteps.fillField("пароль", "hrmhrm123");
        authorizationSteps.clickSignInButton("войти");
        authorizationSteps.setPage("DjangoEmployeeChange");
        webActionSteps.fillTheField("Отчество","Валентиновна");
        webActionSteps.fillTheField("Дата приема на работу","20.08.2020");
        webActionSteps.fillTheField("Телефон","+79883456789");
        webActionSteps.fillTheField("День рождения","21.06.1988");
        webActionSteps.listSelectRandElement("Гражданство");
        webActionSteps.clickOnButton("Сохранить и продолжить редактирование");
        employeeCheckSteps.matchText("Сообщение о успешном редактировании", "The Сотрудник “Бородкина Анастасия” was changed successfully. You may edit it again below.");
    }
}

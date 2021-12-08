import io.qameta.allure.Description;
import org.testng.annotations.Test;
import ru.lanit.at.web.pagecontext.PageManager;
import steps.*;

public class tk_8_9 {
    private PageManager pageManager = new PageManager();

    @Test
    @Description("Тест-кейс 8.9")
    public void test81() {
        AuthorizationSteps authorizationSteps = new AuthorizationSteps(pageManager);
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

        employeeSteps.scrollToElement("Проект");
        authorizationSteps
                .clickSignInButton("Проект");
        employeeSteps.scrollToElement("Добавить проект");
        authorizationSteps
                .clickSignInButton("Добавить проект");
        webActionSteps.listSelectRandElement("Выбор проекта");
        webActionSteps.listSelectRandElement("Вид тестирования");
        webActionSteps.listSelectRandElement("Проектная роль");
//        Допилить RRRRRRDDDDD
        webActionSteps.generateFillTheField("Обратная связь", "RRRRRRDDDDD");
        webActionSteps.generateFillTheField("Дата начала", "20.08.2020");
        authorizationSteps
                .clickSignInButton("Дата окончания: Сегодня");
//        Допилить RRRRRRDDDDD
        webActionSteps.generateFillTheField("Обязанности", "RRRRRRDDDDD");
        authorizationSteps
                .clickSignInButton("Дата окончания: Сегодня");
        authorizationSteps
                .clickSignInButton("СТАЖЕР");
        authorizationSteps
                .clickSignInButton("Сохранить и продолжить редактирование");
        employeeCheckSteps.matchText("Сообщение о успешном редактировании",
                "The Сотрудник “Бородкина Анастасия” was changed successfully. You may edit it again below.");
    }
}

package ts8;

import hooks.WebHooks;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import ru.lanit.at.web.pagecontext.PageManager;
import steps.*;

public class ChangeEmployeePositiveTest extends WebHooks {
    private final PageManager pageManager = new PageManager();

    private final AuthorizationSteps authorizationSteps = new AuthorizationSteps(pageManager);
    private final WebCheckSteps webCheckSteps = new WebCheckSteps(pageManager);
    private final WebActionSteps webActionSteps = new WebActionSteps(pageManager);
    private final EmployeeSteps employeeSteps = new EmployeeSteps(pageManager);
    private final EmployeeCheckSteps employeeCheckSteps = new EmployeeCheckSteps(pageManager);

    @Test(priority = 1)
    @Description("8.1. Проверка редактирования ключевых полей." +
            " Django Administration Роль just_employee")
    public void checkEditingUniqueFields() {
        authorizationSteps.openUrl();
        authorizationSteps.setPage("DjangoAuthorization");
        authorizationSteps.fillField("логин", "just_employee");
        authorizationSteps.fillField("пароль", "hrmhrm123");
        webActionSteps
                .clickOnButton("войти");
        authorizationSteps.setPage("DjangoEmployeeChange");

        webCheckSteps.checkFieldText("Фамилия", "Бородкин");
        webCheckSteps.checkFieldText("Имя", "Битард");
        webCheckSteps.listCheckedElement("Пол", "Male");
        webCheckSteps.checkElementIsReadOnly("Корпоративная почта");

        webActionSteps.fillTheField("Фамилия", "Бородкина");
        webActionSteps.fillTheField("Имя", "Анастасия");

        employeeSteps.setInDropDown("Пол", "Female");
        webActionSteps.clickOnButton("Сохранить и продолжить редактирование");

        employeeCheckSteps.matchText("Сообщение о успешном редактировании",
                "The Сотрудник “Бородкина Анастасия” was changed successfully. You may edit it again below.");

        webCheckSteps.checkFieldText("Фамилия", "Бородкина");
        webCheckSteps.checkFieldText("Имя", "Анастасия");
        webCheckSteps.listCheckedElement("Пол", "Female");
        webActionSteps.closeDriver();
    }

    @Test(priority = 2)
    @Description("8.2. Проверка редактирования второстепенных полей." +
            " Django Administration Роль just_employee")
    public void checkEditingSecondFields() {
        authorizationSteps.openUrl();
        authorizationSteps.setPage("DjangoAuthorization");

        authorizationSteps.fillField("логин", "just_employee");
        authorizationSteps.fillField("пароль", "hrmhrm123");
        webActionSteps.clickOnButton("войти");
        authorizationSteps.setPage("DjangoEmployeeChange");
        webActionSteps.fillTheField("Отчество","Валентиновна");
        webActionSteps.fillTheField("Дата приема на работу","20.08.2020");
        webActionSteps.fillTheField("Телефон","+79883456789");
        webActionSteps.fillTheField("День рождения","21.06.1988");
        webActionSteps.listSelectRandElement("Гражданство");
        webActionSteps.clickOnButton("Сохранить и продолжить редактирование");
        employeeCheckSteps.matchText("Сообщение о успешном редактировании",
                "The Сотрудник “Бородкина Анастасия” was changed successfully. You may edit it again below.");
        webActionSteps.closeDriver();
    }

    @Test(priority = 3)
    @Description("8.3. Проверка добавления фотографии." +
            " Django Administration Роль just_employee")
    public void checkAddingPhoto(){
        authorizationSteps.openUrl();
        authorizationSteps.setPage("DjangoAuthorization");
        authorizationSteps.fillField("логин", "just_employee");
        authorizationSteps.fillField("пароль", "hrmhrm123");
        webActionSteps
                .clickOnButton("войти");
        authorizationSteps.setPage("DjangoEmployeeChange");

        webActionSteps.uploadFiles("Выберите фото", "test.jpg");
//        #    Если на текущей странице 'Выберите фото' с текстом '<filename>' присутствует
        webActionSteps.clickOnButton("Сохранить и продолжить редактирование");
        employeeCheckSteps.curFieldsContainsThatElement("Name");
        employeeCheckSteps.curFieldsContainsThatElement("Загруженное фото");
        webActionSteps.clickOnButton("Очистить фото");
        webActionSteps.clickOnButton("Сохранить");
        webActionSteps.closeDriver();
    }

    @Test(priority = 4)
    @Description("8.4. Проверка замены загруженной фотографии." +
            " Django Administration Роль just_employee")
    public void checkChangingUploadPhoto(){
        authorizationSteps.openUrl();
        authorizationSteps.setPage("DjangoAuthorization");
        authorizationSteps.fillField("логин", "just_employee");
        authorizationSteps.fillField("пароль", "hrmhrm123");
        webActionSteps
                .clickOnButton("войти");
        authorizationSteps.setPage("DjangoEmployeeChange");

        webActionSteps.uploadFiles("Выберите фото", "test.jpg");
        webActionSteps.clickOnButton("Сохранить и продолжить редактирование");
        employeeCheckSteps.curFieldsContainsThatElement("Загруженное фото");
        webActionSteps.uploadFiles("Выберите фото", "test.png");
//        #    Если на текущей странице 'Выберите фото' с текстом '<filename>' присутствует
        webActionSteps.clickOnButton("Сохранить и продолжить редактирование");
        employeeCheckSteps.curFieldsContainsThatElement("Загруженное фото");
        employeeCheckSteps.curFieldsContainsThatElement("Name");
        webActionSteps.clickOnButton("Очистить фото");
        webActionSteps.clickOnButton("Сохранить");
        webActionSteps.closeDriver();
    }

    @Test(priority = 5)
    @Description("8.5. Проверка удаления загруженной фотографии." +
            " Django Administration Роль just_employee")
    public void checkRemovingUploadedPhoto(){
        authorizationSteps.openUrl();
        authorizationSteps.setPage("DjangoAuthorization");
        authorizationSteps.fillField("логин", "just_employee");
        authorizationSteps.fillField("пароль", "hrmhrm123");
        webActionSteps
                .clickOnButton("войти");
        authorizationSteps.setPage("DjangoEmployeeChange");

        webActionSteps.uploadFiles("Выберите фото", "test.jpg");
        webActionSteps.clickOnButton("Сохранить и продолжить редактирование");
        employeeCheckSteps.curFieldsContainsThatElement("Загруженное фото");
        webActionSteps.clickOnButton("Очистить фото");
        webActionSteps.clickOnButton("Сохранить и продолжить редактирование");
        employeeCheckSteps.curFieldsContainsThatElement("Name");
        employeeCheckSteps.matchText("Инфоблок фото", "Фотография отсутствует");
        webActionSteps.closeDriver();
    }

    @Test(priority = 6)
    @Description("8.6. Блок \"Квалификация\", проверка редактирования." +
            " Django Administration Роль just_employee")
    public void checkEditingQualityBlock(){
        authorizationSteps.openUrl();
        authorizationSteps.setPage("DjangoAuthorization");
        authorizationSteps.fillField("логин", "just_employee");
        authorizationSteps.fillField("пароль", "hrmhrm123");
        webActionSteps
                .clickOnButton("войти");
        authorizationSteps.setPage("DjangoEmployeeChange");

        employeeSteps.scrollToElement("Квалификация");
        webActionSteps
                .clickOnButton("Квалификация");
        employeeSteps.clickRandomN("Все квалификации", 5);
        employeeSteps.unClickRandomN("Все квалификации", 3);
        webActionSteps
                .clickOnButton("Сохранить и продолжить редактирование");
        employeeCheckSteps.matchText("Сообщение о успешном редактировании",
                "The Сотрудник “Бородкина Анастасия” was changed successfully.");
        webActionSteps.closeDriver();
    }

    @Test(priority = 7)
    @Description("8.7. Блок \"Навыки\", проверка редактирования." +
            " Django Administration Роль just_employee")
    public void checkEditingSkillsBlock(){
        authorizationSteps.openUrl();
        authorizationSteps.setPage("DjangoAuthorization");
        authorizationSteps.fillField("логин", "just_employee");
        authorizationSteps.fillField("пароль", "hrmhrm123");
        webActionSteps
                .clickOnButton("войти");
        authorizationSteps.setPage("DjangoEmployeeChange");
        employeeSteps.scrollToElement("Навыки");
        webActionSteps
                .clickOnButton("Навыки");
        employeeSteps.clickRandomN("Все навыки", 5);
        employeeSteps.unClickRandomN("Все навыки", 3);
        webActionSteps
                .clickOnButton("Сохранить и продолжить редактирование");
        employeeCheckSteps.matchText("Сообщение о успешном редактировании",
                "The Сотрудник “Бородкина Анастасия” was changed successfully.");
        webActionSteps.closeDriver();
    }

    @Test(priority = 8)
    @Description("8.8. Блок \"Сертификаты\", проверка редактирования." +
            " Django Administration Роль just_employee")
    public void checkEditingCertificatesBlock() {
        authorizationSteps.openUrl();
        authorizationSteps.setPage("DjangoAuthorization");
        authorizationSteps.fillField("логин", "just_employee");
        authorizationSteps.fillField("пароль", "hrmhrm123");
        webActionSteps
                .clickOnButton("войти");
        authorizationSteps.setPage("DjangoEmployeeChange");

        employeeSteps.scrollToElement("Сертификаты");
        webActionSteps.clickOnButton("Сертификаты");
        webActionSteps.generateFillTheField("Сертификаты:", "RRRRRRDDDDD");
        webActionSteps.generateFillTheField("Курсы:", "RRRRRRDDDDD");
        webActionSteps
                .clickOnButton("Сохранить и продолжить редактирование");
        employeeCheckSteps.matchText("Сообщение о успешном редактировании",
                "The Сотрудник “Бородкина Анастасия” was changed successfully. You may edit it again below.");
        webActionSteps.closeDriver();
    }

    @Test(priority = 9)
    @Description("8.9. Блок \"Проекты\", проверка редактирования." +
            " Django Administration Роль just_employee")
    public void checkEditingProjectsBlock() {
        authorizationSteps.openUrl();
        authorizationSteps.setPage("DjangoAuthorization");
        authorizationSteps.fillField("логин", "just_employee");
        authorizationSteps.fillField("пароль", "hrmhrm123");
        webActionSteps
                .clickOnButton("войти");
        authorizationSteps.setPage("DjangoEmployeeChange");

        employeeSteps.scrollToElement("Проект");
        webActionSteps
                .clickOnButton("Проект");
        employeeSteps.scrollToElement("Добавить проект");
        webActionSteps
                .clickOnButton("Добавить проект");
        webActionSteps.listSelectRandElement("Выбор проекта");
        webActionSteps.listSelectRandElement("Вид тестирования");
        webActionSteps.listSelectRandElement("Проектная роль");
        webActionSteps.generateFillTheField("Обратная связь", "RRRRRRDDDDD");
        webActionSteps.generateFillTheField("Дата начала", "20.08.2020");
        webActionSteps
                .clickOnButton("Дата окончания: Сегодня");
        webActionSteps.generateFillTheField("Обязанности", "RRRRRRDDDDD");
        webActionSteps
                .clickOnButton("Дата окончания: Сегодня");
        webActionSteps
                .clickOnButton("СТАЖЕР");
        webActionSteps
                .clickOnButton("Сохранить и продолжить редактирование");
        employeeCheckSteps.matchText("Сообщение о успешном редактировании",
                "The Сотрудник “Бородкина Анастасия” was changed successfully. You may edit it again below.");
        webActionSteps.closeDriver();
    }

    @Test(priority = 10)
    @Description("8.10. Проверка работоспособности кнопки \"СОХРАНИТЬ\"." +
            " Django Administration Роль just_employee")
    public void checkSaveButton() {
        authorizationSteps.openUrl();
        authorizationSteps.setPage("DjangoAuthorization");

        webActionSteps
                .clickOnButton("Сохранить");

        employeeCheckSteps.matchText("Сообщение о успешном редактировании",
                "The Сотрудник “Бородкина Анастасия” was changed successfully.");
        webActionSteps.closeDriver();
    }
}

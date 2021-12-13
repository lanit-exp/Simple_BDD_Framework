package ts7;

import hooks.WebHooks;
import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.lanit.at.web.pagecontext.PageManager;
import steps.WebCheckSteps;
import steps.WebSteps;

public class AddEmployeePage1Test extends WebHooks {
    private PageManager pageManager = new PageManager();
    private WebSteps webSteps = new WebSteps(pageManager);
    private WebCheckSteps webCheckSteps = new WebCheckSteps(pageManager);

    @DataProvider
    public Object[][] testDataAddEmployeePositiveHrBlocks() {
        return new Object[][]{
                {"Грейды", "Грейд", "Заметка Грейды"},
                {"Проектные ставки", "Проектная ставка", "Проект Проектные ставки"},
                {"Договоры", "Договор", "Договоры Должность"},
        };
    }

    @DataProvider
    public Object[][] testDataAddEmployeePositiveHrBlocks2() {
        return new Object[][]{
                {"Города", "Город", "Город"},
                {"Иностранные языки", "Иностранный язык", "Иностранный язык"},
                {"Предыдущий опыт работы", "Предыдущий опыт работы", "Название организации опыта"},
                {"Достижения", "Достижение", "Достижение"},
                {"Запросы на отпуск", "Запрос на отпуск", "Статус запроса"},
        };
    }

    @Test
    @Description(value = "7.21 Заполнение блока Должности под ролью HR")
    public void addEmployeePositiveHrFillingPositionBlock() {
        hrAuthorizationAndAddEmployee();
        webSteps.scrollToElement("Должности");
        webSteps.clickOnElement("Должности");
        webCheckSteps.checkAppearElement("Добавить еще один Должность");
        webSteps.clickOnElement("Добавить еще один Должность");
        webCheckSteps.checkReadOnly("Изменить выбранный объект типа Должность", "href");
        webSteps.clickRandom("Должность");
        webCheckSteps.checkElementIsRead("Изменить выбранный объект типа Должность");
        webSteps.clickRandom("Название организации");
        webCheckSteps.checkElementIsRead("Изменить выбранный объект типа Должность");
        webSteps.setRandomInt("Ставка", -5, 5);
        webSteps.setRandomInt("Грейд", -5, 5);
        webSteps.fillField("Дата начала должности", "01.01.2001");
        webSteps.fillField("Дата окончания должности", "01.01.2021");
        webSteps.fillFieldRandomString("Заметка Должности");
        webSteps.clickOnElement("Скрыть");
        webCheckSteps.currentTextIsNotExist("Должность");
    }

    @Test(dataProvider = "testDataAddEmployeePositiveHrBlocks")
    @Description(value = "7.22/7.24/7.25.1 Работа блока Грейды/Проектные ставки/Договоры/ под ролью HR")
    public void addEmployeePositiveHrCheckBlocksWork(String blocks, String block, String checkbox) {
        hrAuthorizationAndAddEmployee();
        webSteps.scrollToElement(blocks);
        webSteps.clickOnElement(blocks);
        webCheckSteps.checkAppearElement("Добавить еще один " + block);
        webSteps.clickOnElement("Добавить еще один " + block);
        webCheckSteps.checkNoSelectedCheckbox(checkbox);
        webSteps.clickOnElement("Скрыть");
        webCheckSteps.elementAbsentOnPage(checkbox);
    }

    @Test
    @Description(value = "7.23 Заполнения блока Проекты под ролью HR")
    public void addEmployeePositiveHrFillingProjectsBlock() {
        hrAuthorizationAndAddEmployee();
        webSteps.scrollToElement("Проекты");
        webSteps.clickOnElement("Проекты");
        webCheckSteps.checkAppearElement("Добавить еще один Проект");
        webSteps.clickOnElement("Добавить еще один Проект");
        webCheckSteps.checkReadOnly("Изменить выбранный объект типа Проект", "href");
        webSteps.clickRandom("Проекты Проект");
        webCheckSteps.checkElementIsRead("Изменить выбранный объект типа Проект");
        webSteps.clickRandom("Тип тестирования");
        webCheckSteps.checkElementIsRead("Изменить выбранный объект типа Проект");
        webSteps.fillField("Дата начала проекты", "01.01.2001");
        webSteps.fillField("Дата окончания проекты", "01.01.2021");
        webSteps.fillFieldRandomString("Обязанности проекты");
        webSteps.clickOnElement("Чекбокс Стажер");
        webSteps.clickOnElement("Скрыть");
        webCheckSteps.currentTextIsNotExist("Проекты Проект");
    }

    @Test
    @Description(value = "7.25.2 Работа блока Договоры ГПХ под ролью HR")
    public void addEmployeePositiveHrCheckContractsGPHBlockWork() {
        hrAuthorizationAndAddEmployee();
        webSteps.scrollToElement("Договоры ГПХ");
        webSteps.clickOnElement("Договоры ГПХ");
        webCheckSteps.checkAppearElement("Добавить еще один Договор ГПХ");
        webSteps.clickOnElement("Добавить еще один Договор ГПХ");
        webCheckSteps.checkEmptyField("Номер договора");
        webSteps.clickOnElement("Скрыть");
        webCheckSteps.elementAbsentOnPage("Номер договора");
    }

    @Test(dataProvider = "testDataAddEmployeePositiveHrBlocks2")
    @Description(value = "7.26/7.27/7.28/7.29/7.30 Работа блока Города/Иностранные языки/Предыдущий опыт работы/Достижения/Запросы на отпуск под ролью HR")
    public void addEmployeePositiveHrCheckBlock2(String blocks, String block, String checkbox) {
        hrAuthorizationAndChangeEmployee();
        webSteps.scrollToElement(blocks);
        webSteps.clickOnElement(blocks);
        webCheckSteps.checkAppearElement("Добавить еще один " + block);
        webSteps.clickOnElement("Добавить еще один " + block);
        webCheckSteps.checkNoSelectedCheckbox(checkbox);
        webSteps.clickOnElement("Скрыть");
        webCheckSteps.elementAbsentOnPage(checkbox);
    }

    private void hrAuthorizationAndAddEmployee() {
        webSteps.openUrl();
        webSteps.setPage("DjangoAuthorization");
        webCheckSteps.checkNoSelectedCheckbox("Я желаю войти с админскими правами");
        webSteps.clickOnElement("Я желаю войти с админскими правами");
        webCheckSteps.checkNoSelectedCheckbox("Я здесь впервые");
        webSteps.fillField("логин", "hr");
        webSteps.fillField("пароль", "hrmhrm12345");
        webCheckSteps.checkAppearElement("токен");
        webSteps.fillTokenField("токен", "hr", "hrmhrm12345");
        webCheckSteps.checkAppearElement("войти");
        webSteps.clickOnElement("войти");
        webCheckSteps.currentTextIsNotExist("Сообщение об ошибке");
        webSteps.setPage("DjangoAdministration");
        webCheckSteps.checkAppearElement("Сотрудники");
        webSteps.clickOnElement("Сотрудники");
        webSteps.setPage("DjangoEmployee");
        webCheckSteps.checkAppearElement("Добавить сотрудник");
        webSteps.clickOnElement("Добавить сотрудник");
        webSteps.setPage("DjangoEmployeeChange");
    }

    private void hrAuthorizationAndChangeEmployee() {
        webSteps.openUrl();
        webSteps.setPage("DjangoAuthorization");
        webCheckSteps.checkNoSelectedCheckbox("Я желаю войти с админскими правами");
        webSteps.clickOnElement("Я желаю войти с админскими правами");
        webCheckSteps.checkNoSelectedCheckbox("Я здесь впервые");
        webSteps.fillField("логин", "hr");
        webSteps.fillField("пароль", "hrmhrm12345");
        webCheckSteps.checkAppearElement("токен");
        webSteps.fillTokenField("токен", "hr", "hrmhrm12345");
        webCheckSteps.checkAppearElement("войти");
        webSteps.clickOnElement("войти");
        webCheckSteps.currentTextIsNotExist("Сообщение об ошибке");
        webSteps.setPage("DjangoAdministration");
        webCheckSteps.checkAppearElement("Сотрудники");
        webSteps.clickOnElement("Сотрудники");
        webSteps.setPage("DjangoEmployee");
        webSteps.clickRandom("ФИО");
        webSteps.setPage("DjangoEmployeeChange");
    }
}
package pages;

import com.codeborne.selenide.SelenideElement;
import ru.lanit.at.web.annotations.Name;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Name(value = "DjangoAdministration")
public class DjangoAdministrationPage extends DjangoPagesHeader {

    @Name("Заголовок таблицы")
    private SelenideElement tableHeader = $x("//div[@id='content']/h1");

    //MAIN
    @Name("Сотрудники")
    private SelenideElement employee = $(".model-employee th a");

    @Name("Запросы на отпуск")
    private SelenideElement employeeVacationRequest = $x("//a[text()='Запросы на отпуск']");
    @Name("Фактические отпуска")
    private SelenideElement employeeActualVacation= $x("//a[text()='Фактические отпуска']");
    @Name("Больничные")
    private SelenideElement employeeSickLeaveOfficial = $x("//a[text()='Больничные']");
    @Name("Бюллютени без больничного листа")
    private SelenideElement employeeSickLeaveUnofficial = $x("//a[text()='Бюллютени без больничного листа']");

    //ACCOUNTS
    @Name("Аккаунты")
    private SelenideElement account = $x("//a[text()='Аккаунты']");
    @Name("Проекты")
    private SelenideElement project = $x("//a[text()='Проекты']");

    //STRUCTURE
    @Name("Департаменты")
    private SelenideElement department = $x("//a[text()='Департаменты']");
    @Name("Должности")
    private SelenideElement position = $x("//a[text()='Должности']");
    @Name("Проектные роли")
    private SelenideElement projectRole = $x("//a[text()='Проектные роли']");
    @Name("Типы тестирования")
    private SelenideElement testingType = $x("//a[text()='Типы тестирования']");

    //DICTIONARY
    @Name("Города")
    private SelenideElement city = $x("//a[text()='Города']");
    @Name("Гражданства")
    private SelenideElement citizenship = $x("//a[text()='Гражданства']");
    @Name("ВУЗы")
    private SelenideElement educationInstitution = $x("//a[text()='ВУЗы']");
    @Name("Достижения")
    private SelenideElement achievement = $x("//a[text()='Достижения']");
    @Name("Должности в сторонних организациях")
    private SelenideElement outsideRole = $x("//a[text()='Должности в сторонних организациях']");
    @Name("Иностранные языки")
    private SelenideElement language = $x("//a[text()='Иностранные языки']");
    @Name("Названия организации")
    private SelenideElement organisation = $x("//a[text()='Названия организации']");
    @Name("Образования")
    private SelenideElement education = $x("//a[text()='Образования']");
    @Name("Показатели квалификации")
    private SelenideElement qualificationSkills = $x("//a[text()='Показатели квалификации']");
    @Name("Специальности (ВУЗ)")
    private SelenideElement educationSpecialty = $x("//a[text()='Специальности (ВУЗ)']");
    @Name("Сторонние организации")
    private SelenideElement outsideOrganisation = $x("//a[text()='Сторонние организации']");
    @Name("Типы навыков")
    private SelenideElement keySkillType = $x("//a[text()='Типы навыков']");
    @Name("Типы квалификации")
    private SelenideElement qualificationSkillType = $x("//a[text()='Типы квалификации']");
    @Name("Уровни знания иностранного языка")
    private SelenideElement languageKnowledgeLevel = $x("//a[text()='Уровни знания иностранного языка']");
    @Name("Статусы запроса на отпуск")
    private SelenideElement vacationRequestStatus = $x("//a[text()='Статусы запроса на отпуск']");
    @Name("Тип родства")
    private SelenideElement familyType = $x("//a[text()='Тип родства']");

    //ADMINISTRATIVE ACCESS
    @Name("Сотрудники с административным доступом")
    private SelenideElement administrator = $x("//a[text()='Сотрудники с административным доступом']");

    @Name("Добавить поле Сотрудники")
    private SelenideElement addEmployee = $x("//a[@href='/admin/core/employee/add/']");
    @Name("Изменить поле Сотрудники")
    private SelenideElement changeEmployee = $x("//a[@href='/admin/core/employee/' and @class='changelink']");
    @Name("Администрирование Django")
    private SelenideElement headerPage = $x("//a[@href='/admin/']");
}

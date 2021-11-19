package pages.employee;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.DjangoPagesHeader;
import ru.lanit.at.web.annotations.Name;

import static com.codeborne.selenide.Selenide.*;

@Name(value = "DjangoEmployeeChange")
public class DjangoEmployeeChangePage extends DjangoPagesHeader {

    @Name("Имя")
    private SelenideElement firstName = $("#id_name");
    @Name("Фамилия")
    private SelenideElement lastName = $("#id_surname");
    @Name("Фамилия Public")
    private SelenideElement lastNamePublic = $x("//div[@class='fieldBox field-surname']/div[@class='readonly']");
    @Name("Отчество")
    private SelenideElement patronymic = $("#id_patronymic");
    @Name("Пол")
    private SelenideElement dropdownGender = $("#id_gender");
    @Name("Выберите фото")
    private SelenideElement photo = $("#id_photo");
    @Name("Дата приема на работу")
    private SelenideElement joiningDate = $("#id_joining_date");
    @Name("День рождения")
    private SelenideElement birthday = $("#id_birth");
    @Name("Телефон")
    private SelenideElement phone = $("#id_phone");
    @Name("Гражданство")
    private SelenideElement dropdownCitizenship = $("#id_citizenship");
    @Name("Email")
    private SelenideElement email = $("#id_internal_email");
    @Name("Корпоративная почта")
    private SelenideElement corporateEmail = $(".field-internal_email .readonly");

    @Name("Сообщение об ошибке в заголовке")
    private SelenideElement headerError = $(".errornote");
    @Name("Сообщение об ошибке Имя")
    private SelenideElement errorFirstName = $x("//div[contains(@class, 'field-name')]/ul/li");
    @Name("Сообщение об ошибке Фамилия")
    private SelenideElement errorSurname = $x("//div[contains(@class, 'field-surname')]/ul/li");
    @Name("Сообщение об ошибке Пол")
    private SelenideElement errorGender = $x("//div[contains(@class, 'field-gender')]/ul/li");
    @Name("Сообщение об ошибке Дата приема на работу")
    private SelenideElement errorJoiningDate = $x("//div[contains(@class, 'field-joining_date')]/ul/li");
    @Name("Сообщение об ошибке Телефон")
    private SelenideElement errorPhone = $x("//div[contains(@class, 'field-phone')]/ul/li");
    @Name("Сообщение об ошибке Email")
    private SelenideElement errorEmail = $x("//div[contains(@class, 'field-internal_email')]/ul/li");
    @Name("Сообщение о успешном редактировании")
    private SelenideElement successEdit = $x("//div[contains(@id, 'container')]/ul/li");

    @Name("История")
    private SelenideElement history = $x("//a[@class='historylink' and text()='История']");
    @Name("Summary")
    private SelenideElement summary = $x("//a[@class='historylink' and text()='Summary']");
    @Name("Квалификация")
    private SelenideElement qualification = $x("//div[contains(@id, 'content')]/div/form/div/fieldset/h2[text()=\"Квалификация\"]/a");
    @Name("Все квалификации")
    private ElementsCollection AllQualifications = $$x("//ul[@id='id_qualification_skill']/li/label/input");

    @Name("Удалено")
    private SelenideElement delete = $(".deletelink");
    @Name("Сохранить и добавать другой объект")
    private SelenideElement saveAndAddAnother = $x("//input[@name='_addanother']");
    @Name("Сохранить и продолжить редактирование")
    private SelenideElement saveAndContinue = $x("//input[@name='_continue']");
    @Name("Сохранить")
    private SelenideElement save = $x("//input[@name='_save']");

    @Name("Name")
    private SelenideElement nameOfHeader = $x("//li[@class='success']/a");

    @Name("Города")
    private SelenideElement cities = $x("//a[@id='fieldsetcollapser11']");
    @Name("Список городов")
    private SelenideElement listOfcities = $x("//select[@name='employeecity_set-0-city']");
    @Name("Дата изменения")
    private SelenideElement dateOfChange = $x("//th[@class='column-change_date required'][1]");
    @Name("Сегодня")
    private ElementsCollection todayCity = $$x("//span[@class='datetimeshortcuts']/a[text()='Сегодня']");

    @Name("Должности")
    private SelenideElement employerPost = $x("//a[@id='fieldsetcollapser5']");
    @Name("Добавить еще один Должность")
    private SelenideElement addPost = $x("//a[@href  and text()='Добавить еще один Должность']");
    @Name("Изменить выбранный объект типа Должность")
    private SelenideElement changeObjectPost = $x("//a[@id='change_id_employeeposition_set-3-position']/img[@src='/staticfiles/admin/img/icon-changelink.svg']");
    @Name("Должность")
    private ElementsCollection postOfEmployer = $$x("//select[@name='employeeposition_set-3-position']");
    @Name("Название организации")
    private ElementsCollection nameOfOrganization = $$x("//select[@name='employeeposition_set-3-organisation']");
    @Name("Ставка")
    private SelenideElement rateOfEmployer = $x("//input[@name='employeeposition_set-3-rate']");
    @Name("Грейд")
    private SelenideElement gradeOfEmployer = $x("//input[@name='employeeposition_set-3-grade']");
    @Name("Дата начала")
    private SelenideElement dateOfStart = $x("//input[@name='employeeposition_set-3-start_date']");
    @Name("Дата окончания")
    private SelenideElement dateOfFinish = $x("//input[@name='employeeposition_set-3-end_date']");
    @Name("Заметка Должности")
    private SelenideElement pointOfPosts = $x("//textarea[@name='employeeposition_set-3-memo']");

    @Name("Проектные ставки")
    private SelenideElement projectBids = $x("//a[@id='fieldsetcollapser8']");
    @Name("Добавить еще один Проектная ставка")
    private SelenideElement addProjectBid = $x("//a[@href  and text()='Добавить еще один Проектная ставка']");
    @Name("Проект Проектные ставки")
    private SelenideElement fieldProjectP = $x("//select[@name='employeeprojectsalaryrate_set-0-employee_project']");
    @Name("Скрыть")
    private SelenideElement hideProjectBids = $x("//fieldset[@class='module collapse']/h2/a[@class='collapse-toggle']");

    @Name("Договоры")
    private SelenideElement contracts = $x("//a[@id='fieldsetcollapser9']");
    @Name("Добавить еще один Договор")
    private SelenideElement addAnotherContract = $x("//a[@href  and text()='Добавить еще один Договор']");
    @Name("Договоры Должность")
    private SelenideElement fieldPost = $x("//select[@name='contract-0-position']");
}


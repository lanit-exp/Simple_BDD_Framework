package pages.employee;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pages.DjangoPagesHeader;
import ru.lanit.at.web.annotations.Name;

import static com.codeborne.selenide.Selenide.*;

@Name(value = "DjangoEmployeeChange")
public class DjangoEmployeeChangePage extends DjangoPagesHeader {

    @Name("Имя")
    private SelenideElement firstName = $("#id_name");
    @Name("Фамилия")
    private SelenideElement lastName = $("#id_surname");
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
    @Name("Навыки")
    private SelenideElement skills = $x("//div[contains(@id, 'content')]/div/form/div/fieldset/h2[text()=\"Навыки\"]/a");
    @Name("Сертификаты")
    private SelenideElement certification = $x("//div[contains(@id, 'content')]/div/form/div/fieldset/h2[text()=\"Сертификаты\"]/a");
    @Name("Все квалификации")
    private ElementsCollection AllQualifications = $$x("//ul[@id='id_qualification_skill']/li/label/input");
    @Name("Все навыки")
    private ElementsCollection allSkills = $$x("//ul[@id='id_key_skill']/li/label/input");
    @Name("Сертификаты:")
    private SelenideElement AllCertification = $x("//textarea[@id='id_certificates']");
    @Name("Курсы:")
    private SelenideElement AllCourses = $x("//textarea[@id='id_courses']");
    @Name("Проект")
    private SelenideElement project = $x("//fieldset/h2[text()=\"Проекты\"]/a");
    @Name("Добавить проект")
    private SelenideElement newProject = $x("//tr[@id=\"employeeproject_set-empty\"]/following-sibling::tr/td/a");
    @Name("Новый проект")
    private SelenideElement lastProject = $x("//div[@id='employeeproject_set-group']//fieldset/table/tbody/tr[@id = 'employeeproject_set-1']");
    @Name("Выбор проекта")
    private SelenideElement projectName = lastProject.find(By.xpath("./td[@class = 'field-project']/div/select"));
    @Name("Вид тестирования")
    private SelenideElement testingType = lastProject.find(By.xpath("./td[@class = 'field-testing_type']/div/select"));
    @Name("Проектная роль")
    private SelenideElement projectRole = lastProject.find(By.xpath("./td[@class = 'field-project_role']/div/select"));
    @Name("Обратная связь")
    private SelenideElement feedBack = lastProject.find(By.xpath("./td[@class = 'field-feedback']/textarea"));
    @Name("Дата начала")
    private SelenideElement startDate = lastProject.find(By.xpath("./td[@class = 'field-start_date']/input"));
    @Name("Дата окончания: Сегодня")
    private SelenideElement endDateToday = lastProject.find(By.xpath("./td[@class = 'field-end_date']/span/a"));
    @Name("Обязанности")
    private SelenideElement responsibilities = lastProject.find(By.xpath("./td[@class = 'field-responsibilities']/textarea"));
    @Name("СТАЖЕР")
    private SelenideElement trainee = lastProject.find(By.xpath("./td[@class = 'field-is_trainee']/input"));
    @Name("Удалить проект")
    private SelenideElement deleteProject = lastProject.find(By.xpath("./td[@class = 'field-is_trainee']/input"));





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

}


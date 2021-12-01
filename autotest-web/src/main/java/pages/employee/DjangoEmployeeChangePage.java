package pages.employee;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.DjangoPagesHeader;
import ru.lanit.at.web.annotations.Name;

import static com.codeborne.selenide.Selenide.*;

@Name(value = "DjangoEmployeeChange")
public class DjangoEmployeeChangePage extends DjangoPagesHeader {

    @Name("Имя")
    private SelenideElement firstName = $x("//input[@id='id_name']");
    @Name("Фамилия")
    private SelenideElement lastName = $x("//input[@id='id_surname']");
    @Name("Отчество")
    private SelenideElement patronymic = $x("//input[@id='id_patronymic']");
    @Name("Пол")
    private SelenideElement dropdownGender = $x("//select[@id='id_gender']");
    @Name("Выберите фото")
    private SelenideElement photo = $x("//input[@id='id_photo']");
    @Name("Дата приема на работу")
    private SelenideElement joiningDate = $x("//input[@id='id_joining_date']");
    @Name("День рождения")
    private SelenideElement birthday = $x("//input[@id='id_birth']");
    @Name("Телефон")
    private SelenideElement phone = $x("//input[@id='id_phone']");

    @Name("Гражданство")
    private SelenideElement dropdownCitizenship = $x("//select[@id='id_citizenship']");
    @Name("Изменить выбранный объект типа Гражданство")
    private SelenideElement changeCitizenship = $x("//a[@id='change_id_citizenship']");
    @Name("Добавить еще один объект типа Гражданство")
    private SelenideElement addCitizenship = $x("//a[@id='add_id_citizenship']");
    @Name("Удалить выбранный объект типа Гражданство")
    private SelenideElement deleteCitizenship = $x("//a[@id='delete_id_citizenship']");

    @Name("Показать Квалификация")
    private SelenideElement showQualification = $x("//a[@id='fieldsetcollapser0']");
    @Name("Скрыть Квалификация")
    private SelenideElement hideQualification = $x("//a[@id='fieldsetcollapser0']");
    @Name("Список квалификаций")
    private ElementsCollection keyQualification = $$x("//ul[@id='id_qualification_skill']/li");
    @Name("Добавить еще один объект типа Квалификация")
    private SelenideElement addQualificationSkill = $x("//a[@id='add_id_qualification_skill']");
    @Name("Общие квалификации: Автоматизированное тестирование")
    private SelenideElement qualificationSkill0 = $x("//input[@id='id_qualification_skill_0']");

    @Name("Показать Навыки")
    private SelenideElement showSkills = $x("//a[@id='fieldsetcollapser1']");
    @Name("Скрыть Навыки")
    private SelenideElement hideSkills = $x("//a[@id='fieldsetcollapser1']");
    @Name("Список навыков")
    private ElementsCollection keySkill = $$x("//ul[@id='id_key_skill']/li");
    @Name("Языки программирования: C# 5.0")
    private SelenideElement keySkill0 = $x("//input[@id='id_key_skill_0']");

    @Name("Показать Статус сотрудника")
    private SelenideElement showEmployeeStatus = $x("//a[@id='fieldsetcollapser3']");
    @Name("Скрыть Статус сотрудника")
    private SelenideElement hideEmployeeStatus = $x("//a[@id='fieldsetcollapser3']");
    @Name("Список статусов сотрудников")
    private ElementsCollection keyEmployeeStatus = $$x("//div/input[@type='checkbox']/parent::div");
    @Name("Работает")
    private SelenideElement isActive = $x("//input[@id='id_is_active']");

    @Name("Показать ОБРАЗОВАНИЯ")
    private SelenideElement showEducation = $x("//a[@id='fieldsetcollapser4']");
    @Name("Скрыть ОБРАЗОВАНИЯ")
    private SelenideElement hideEducation = $x("//a[@id='fieldsetcollapser4']");
    @Name("Добавить еще один Образование")
    private SelenideElement addEducation = $x("//a[text()='Добавить еще один Образование']");
    @Name("Изменить выбранный объект типа ВУЗ")
    private SelenideElement editEducation = $x("//a[@id='change_id_employeeeducation_set-0-institution']");
    @Name("ВУЗ")
    private SelenideElement institution = $x("//select[@id='id_employeeeducation_set-0-institution']");
    @Name("УРОВЕНЬ ОБРАЗОВАНИЯ")
    private SelenideElement degree = $x("//select[@id='id_employeeeducation_set-0-degree']");
    @Name("Изменить выбранный объект типа УРОВЕНЬ ОБРАЗОВАНИЯ")
    private SelenideElement editDegree = $x("//a[@id='change_id_employeeeducation_set-0-degree']");
    @Name("СПЕЦИАЛЬНОСТЬ")
    private SelenideElement specialty = $x("//select[@id='id_employeeeducation_set-0-specialty']");
    @Name("Изменить выбранный объект типа СПЕЦИАЛЬНОСТЬ")
    private SelenideElement editSpecialty = $x("//a[@id='change_id_employeeeducation_set-0-specialty']");
    @Name("ДАТА НАЧАЛА")
    private SelenideElement startDateOfEducation = $x("//input[@id='id_employeeeducation_set-0-start_date']");
    @Name("ДАТА ОКОНЧАНИЯ")
    private SelenideElement endDateOfEducation = $x("//input[@id='id_employeeeducation_set-0-end_date']");

    @Name("Email")
    private SelenideElement email = $x("//input[@id='id_internal_email']");

    @Name("Показать Сертификаты")
    private SelenideElement showCertificates = $x("//a[@id='fieldsetcollapser2']");
    @Name("Скрыть Сертификаты")
    private SelenideElement hideCertificates = $x("//a[@id='fieldsetcollapser2']");
    @Name("Сертификаты")
    private SelenideElement certificates = $x("//textarea[@id='id_certificates']");
    @Name("Курсы")
    private SelenideElement courses = $x("//textarea[@id='id_courses']");

    @Name("Сообщение об ошибке в заголовке")
    private SelenideElement headerError = $x("//p[@class='errornote']");
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

    @Name("История")
    private SelenideElement history = $x("//a[@class='historylink' and text()='История']");
    @Name("Summary")
    private SelenideElement summary = $x("//a[@class='historylink' and text()='Summary']");

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


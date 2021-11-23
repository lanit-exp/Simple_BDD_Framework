package pages.employee;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.eo.Se;
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
    private SelenideElement todayCity = $x("//input[@name='employeecity_set-0-change_date']/following-sibling::span/a[text()='Сегодня']");

    @Name("Должности")
    private SelenideElement employerPost = $x("//a[@id='fieldsetcollapser5']");
    @Name("Добавить еще один Должность")
    private SelenideElement addPost = $x("//a[@href  and text()='Добавить еще один Должность']");
    @Name("Изменить выбранный объект типа Должность")
    private SelenideElement changeObjectPost = $x("//tr[@class='form-row dynamic-employeeposition_set row1']/td[@class='field-position']/div/select/following-sibling::a[@id='change_id_employeeposition_set-0-position']");
    @Name("Должность")
    private ElementsCollection postOfEmployer = $$x("//select[@id='id_employeeposition_set-0-position']/option");
    @Name("Название организации")
    private ElementsCollection nameOfOrganization = $$x("//select[@name='employeeposition_set-0-organisation']/option");
    @Name("Ставка")
    private SelenideElement rateOfEmployer = $x("//input[@name='employeeposition_set-0-rate']");
    @Name("Грейд")
    private SelenideElement gradeOfEmployer = $x("//input[@name='employeeposition_set-0-grade']");
    @Name("Дата начала должности")
    private SelenideElement dateOfStart = $x("//input[@name='employeeposition_set-0-start_date']");
    @Name("Дата окончания должности")
    private SelenideElement dateOfFinish = $x("//input[@name='employeeposition_set-0-end_date']");
    @Name("Заметка Должности")
    private SelenideElement pointOfPosts = $x("//textarea[@name='employeeposition_set-0-memo']");

    @Name("Грейды")
    private SelenideElement grades = $x("//a[@id='fieldsetcollapser6']");
    @Name("Добавить еще один Грейд")
    private SelenideElement addAnotherGrade = $x("//a[@href  and text()='Добавить еще один Грейд']");
    @Name("Заметка Грейды")
    private SelenideElement pointOfGrades = $x("//textarea[@name='employeepositiongrade_set-0-memo']");

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

    @Name("Договоры ГПХ")
    private SelenideElement contractsGpx = $x("//a[@id='fieldsetcollapser10']");
    @Name("Добавить еще один Договор ГПХ")
    private SelenideElement addAnotherContractGpx = $x("//a[@href and text()='Добавить еще один Договор ГПХ']");
    @Name("Номер договора")
    private SelenideElement numberOfContract = $x("//input[@name='employeecivilcontract_set-0-number']");

    @Name("Добавить еще один Город")
    private SelenideElement addAnotherCity = $x("//a[@href and text()='Добавить еще один Город']");
    @Name("Город")
    private SelenideElement fieldCity = $x("//select[@name='employeecity_set-0-city']");

    @Name("Иностранные языки")
    private SelenideElement foreignLanguages = $x("//a[@id='fieldsetcollapser12']");
    @Name("Добавить еще один Иностранный язык")
    private SelenideElement addAnotherLanguage = $x("//a[@href and text()='Добавить еще один Иностранный язык']");
    @Name("Иностранный язык")
    private SelenideElement forLanguage = $x("//select[@name='employeelanguage_set-0-language']");

    @Name("Предыдущий опыт работы")
    private SelenideElement previousExperience = $x("//a[@id='fieldsetcollapser13']");
    @Name("Добавить еще один Предыдущий опыт работы")
    private SelenideElement addAnotherExperience = $x("//a[@href and text()='Добавить еще один Предыдущий опыт работы']");
    @Name("Название организации опыта")
    private SelenideElement nameOrganizExp = $x("//select[@name='employeepreviousexperience_set-0-organisation']");

    @Name("Достижения")
    private SelenideElement achievements = $x("//a[@id='fieldsetcollapser14']");
    @Name("Добавить еще один Достижение")
    private SelenideElement addAnotherAchievement = $x("//a[@href and text()='Добавить еще один Достижение']");
    @Name("Достижение")
    private SelenideElement achievement = $x("//select[@name='employeeachievement_set-0-achievement']");

    @Name("Запросы на отпуск")
    private SelenideElement vacationRequests = $x("//a[@id='fieldsetcollapser15']");
    @Name("Добавить еще один Запрос на отпуск")
    private SelenideElement addAnotherVacRequest = $x("//a[@href and text()='Добавить еще один Запрос на отпуск']");
    @Name("Статус запроса")
    private SelenideElement vacRequestStatus = $x("//select[@name='employeevacationrequest_set-0-request_status']");

    @Name("Проекты")
    private SelenideElement projects = $x("//a[@id='fieldsetcollapser7']");
    @Name("Добавить еще один Проект")
    private SelenideElement addProject = $x("//a[@href  and text()='Добавить еще один Проект']");
    @Name("Изменить выбранный объект типа Проект")
    private SelenideElement changeObjectProject = $x("//tr[@class='form-row dynamic-employeeproject_set row1']/td[@class='field-project']/div/select/following-sibling::a[@id='change_id_employeeproject_set-0-project']");
    @Name("Проекты Проект")
    private ElementsCollection projectOfEmployer = $$x("//select[@id='id_employeeproject_set-0-project']/option");
    @Name("Тип тестирования")
    private ElementsCollection typeOfTests = $$x("//select[@name='employeeproject_set-0-testing_type']/option");
    @Name("Проектная роль проекты")
    private ElementsCollection projectPostEmp = $$x("//select[@name='employeeproject_set-0-project_role']/option");
    @Name("Дата начала проекты")
    private SelenideElement dateOfStartProject = $x("//input[@name='employeeproject_set-0-start_date']");
    @Name("Дата окончания проекты")
    private SelenideElement dateOfFinishProject = $x("//input[@name='employeeproject_set-0-end_date']");
    @Name("Обязанности проекты")
    private SelenideElement duties = $x("//textarea[@name='employeeproject_set-0-responsibilities']");
    @Name("Чекбокс Стажер")
    private SelenideElement checkBoxJun = $x("//input[@name='employeeproject_set-0-is_trainee']");
}


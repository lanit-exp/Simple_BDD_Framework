package pages.employee;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.lanit.at.web.annotations.Name;
import ru.lanit.at.web.pagecontext.WebPage;

import static com.codeborne.selenide.Selenide.*;

@Name(value = "DjangoEmployee")
public class DjangoEmployeePage extends WebPage {
    @Name("ФИО")
    private ElementsCollection fio = $$x("//th[@class='field-full_name']/a");

    @Name("Name")
    private SelenideElement nameOfChange = $x("//li[@class='success']/a");

    @Name("Восстановить удаленный сотрудники")
    private SelenideElement recoverLink = $x("//a[@class='recoverlink']");
    @Name("Импорт")
    private SelenideElement importLink = $x("//a[@class='import_link']");
    @Name("Экспорт")
    private SelenideElement exportLink = $x("//a[@class='export_link']");
    @Name("Добавить сотрудник")
    private SelenideElement addEmployeeLink = $x("//a[@class='addlink']");

    @Name("Поиск")
    private SelenideElement search = $x("//input[@id='searchbar']");
    @Name("Найти")
    private SelenideElement searchButton = $x("//input[@type='submit']");

    @Name("Выполнить")
    private SelenideElement executeButton = $x("//button[@type='submit']");
    @Name("Действие")
    private ElementsCollection dropDownAction = $$x("//select[@name='action']/option");
    @Name("Действие меню")
    private SelenideElement action = $x("//select[@name='action']");

    @Name("Количество найденных записей")
    private SelenideElement numberRecords = $x("//p[@class='paginator']");

    @Name("Предупреждение в заголовке")
    private SelenideElement headerWarning = $(".warning");

    @Name("Сообщение об успешном редактирование")
    private SelenideElement headerSuccess = $(".success");

    @Name("Таблица чек-бокс")
    private ElementsCollection tableCheckboxes = $$x("//input[@class='action-select']");

    @Name("Пагинация")
    private ElementsCollection paginator = $$x("//p[@class='paginator']/a");

    @Name("Пагинация текущая страница")
    private SelenideElement currentPaginator = $x("//span[@class='this-page']");

    @Name("Категории фильтров")
    private ElementsCollection filtersCategory = $$x("//div[@id='changelist-filter']/h3");

    @Name("Фильтр")
    private ElementsCollection filters = $$x("//div[@id='changelist-filter']/ul/li");

    @Name("Текущий город")
    private ElementsCollection currentCity = $$x("//td[@class='field-current_city nowrap']");

    @Name("Инфоблок")
    private SelenideElement tableHeader = $x("//div[@id='content']/h1");

    @Name("Выбрано 1 из 100")
    private SelenideElement howMuchSearch = $x("//span[@class='action-counter']");
    @Name("Договор")
    private SelenideElement contractCheckbox = $x("//input[@value='contract']");
    @Name("Формат")
    private SelenideElement formatField = $x("//select[@name='file_format']");
}


package pages.employee;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.DjangoPagesHeader;
import ru.lanit.at.web.annotations.Name;

import static com.codeborne.selenide.Selenide.*;

@Name(value = "DjangoEmployee")
public class DjangoEmployeePage extends DjangoPagesHeader {
    @Name("ФИО")
    private ElementsCollection fio = $$x("//th[@class='field-full_name']/a");

    @Name("Name")
    private SelenideElement nameOfChange = $x("//li[@class='success']/a");

    @Name("Восстановить удаленный сотрудники")
    private SelenideElement recoverLink = $(".recoverlink");
    @Name("Импорт")
    private SelenideElement importLink = $(".import_link");
    @Name("Экспорт")
    private SelenideElement exportLink = $(".export_link");
    @Name("Добавить сотрудник")
    private SelenideElement addEmployeeLink = $(".addlink");

    @Name("Поиск")
    private SelenideElement search = $("#searchbar");
    @Name("Найти")
    private SelenideElement searchButton = $("#searchbar ~ input[type='submit']");

    @Name("Выполнить")
    private SelenideElement executeButton = $(".button");
    @Name("Действие")
    private ElementsCollection dropDownAction = $$x("//select[@name='action']/option");
    @Name("Действие меню")
    private SelenideElement action = $x("//select[@name='action']");

    @Name("Количество найденных записей")
    private SelenideElement numberRecords = $(".paginator");

    @Name("Предупреждение в заголовке")
    private SelenideElement headerWarning = $(".warning");

    @Name("Сообщение об успешном редактирование")
    private SelenideElement headerSuccess = $(".success");

    @Name("Таблица чек-бокс")
    private ElementsCollection tableCheckboxes = $$(".action-select");
}


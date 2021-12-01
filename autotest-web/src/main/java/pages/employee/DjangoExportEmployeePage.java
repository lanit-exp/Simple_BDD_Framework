package pages.employee;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.DjangoPagesHeader;
import ru.lanit.at.web.annotations.Name;

import static com.codeborne.selenide.Selenide.*;

@Name(value = "DjangoExportEmployee")
public class DjangoExportEmployeePage extends DjangoPagesHeader {

    @Name("Инфоблок")
    private SelenideElement tableHeader = $x("//div[@id='content']/h1");

    @Name("Формат")
    private SelenideElement fieldFormat = $x("//select[@id='id_file_format']");

    @Name("Чекбокс")
    private ElementsCollection checkboxes = $$x("//label[contains(@for, 'id_employee_fields_')]");

    @Name("Отправить")
    private SelenideElement buttonSend = $x("//input[@type='submit']");
}


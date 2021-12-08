package pages.employee;

import com.codeborne.selenide.SelenideElement;
import ru.lanit.at.web.annotations.Name;
import ru.lanit.at.web.pagecontext.WebPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Name(value = "DjangoAddCitizenship")
public class DjangoAddCitizenshipPage extends WebPage {
    @Name("Гражданство")
    private SelenideElement citizenship = $x("//input[@id='id_name']");
    @Name("Сохранить")
    private SelenideElement save = $x("//input[@type='submit']");
    @Name("Добавить Гражданство")
    private SelenideElement addCitizenship = $("#content-main");
    @Name("Изменить Гражданство")
    private SelenideElement editCitizenship = $x("//div[@id='content']/child::h1");


}

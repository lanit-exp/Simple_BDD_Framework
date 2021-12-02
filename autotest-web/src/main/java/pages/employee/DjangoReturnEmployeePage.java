package pages.employee;

import com.codeborne.selenide.SelenideElement;
import ru.lanit.at.web.annotations.Name;
import ru.lanit.at.web.pagecontext.WebPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Name(value = "DjangoReturnEmployee")
public class DjangoReturnEmployeePage extends WebPage {

    @Name("Сохранить")
    private SelenideElement save = $x("//input[@name='_save']");

    @Name("Вернуть Бородкинов Битард")
    private SelenideElement returnBorodkinov = $x("//div[@id='content']/h1");

    @Name("поле Фамилии")
    private SelenideElement surname = $x("//input[@name='surname']");

    @Name("Инфоблок")
    private SelenideElement infoblock = $("#employee_form > p");
}


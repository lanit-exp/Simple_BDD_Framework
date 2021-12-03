package pages.employee;

import com.codeborne.selenide.SelenideElement;
import ru.lanit.at.web.annotations.Name;
import ru.lanit.at.web.pagecontext.WebPage;

import static com.codeborne.selenide.Selenide.$x;

@Name(value = "DjangoDeleteEmployee")
public class DjangoDeleteEmployee extends WebPage {

    @Name("Вы уверены?")
    private SelenideElement areYouSure = $x("//h1[text()='Вы уверены?']");

    @Name("Yes, I`m sure")
    private SelenideElement sureYes = $x("//input[@value='Yes, I’m sure']");

    @Name("Успешно удалены 1 Сотрудник.")
    private SelenideElement message = $x("//ul[@class='messagelist']/li");

}

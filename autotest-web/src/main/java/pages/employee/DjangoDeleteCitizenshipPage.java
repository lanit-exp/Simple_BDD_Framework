package pages.employee;

import com.codeborne.selenide.SelenideElement;
import ru.lanit.at.web.annotations.Name;
import ru.lanit.at.web.pagecontext.WebPage;

import static com.codeborne.selenide.Selenide.$x;

@Name(value = "DjangoDeleteCitizenship")
public class DjangoDeleteCitizenshipPage extends WebPage {
    @Name("Вы уверены?")
    private SelenideElement header1 = $x("//div[@id='content']/child::h1");
    @Name("Yes, I’m sure")
    private SelenideElement save = $x("//input[@type='submit']");
}

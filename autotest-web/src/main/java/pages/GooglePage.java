package pages;

import com.codeborne.selenide.SelenideElement;
import ru.lanit.at.annotations.Name;
import ru.lanit.at.pagecontext.WebPage;

import static com.codeborne.selenide.Selenide.$x;

@Name(value = "Google")
public class GooglePage extends WebPage {

    @Name("поле поиска")
    private SelenideElement searchField = $x("//input[@name='q']");
}

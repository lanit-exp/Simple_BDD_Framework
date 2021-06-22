package pages;

import com.codeborne.selenide.SelenideElement;
import ru.lanit.at.web.annotations.Name;
import ru.lanit.at.web.pagecontext.WebPage;

import static com.codeborne.selenide.Selenide.$x;

@Name(value = "Google")
public class GooglePage extends WebPage {

    @Name("поле поиска")
    private SelenideElement searchField = $x("//input[@name='q']");

    @Name("кнопка поиска")
    private SelenideElement searchButton = $x("//*[@role='listbox']/..//*[@name='btnK']");
}

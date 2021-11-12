package pages;

import com.codeborne.selenide.SelenideElement;
import ru.lanit.at.web.annotations.Name;
import ru.lanit.at.web.pagecontext.WebPage;

import static com.codeborne.selenide.Selenide.$x;
@Name(value = "DjangoLogout")
public class DjangoLogoutPage extends WebPage {
    @Name("Не авторизован")
    private SelenideElement loggedOut = $x("//div[@id='content']/h1");

}

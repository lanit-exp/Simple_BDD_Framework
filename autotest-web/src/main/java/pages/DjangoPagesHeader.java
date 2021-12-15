package pages;

import com.codeborne.selenide.SelenideElement;
import ru.lanit.at.web.annotations.Name;
import ru.lanit.at.web.pagecontext.WebPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Deprecated
public class DjangoPagesHeader extends WebPage {

    @Name("Имя пользователя")
    SelenideElement userName = $x("//div[@id='user-tools']/strong");
    @Name("username")
    SelenideElement userTools = $x("//div[@id='user-tools']/strong");

    @Name("Показать сайт")
    SelenideElement viewSite = $x("//div[@id='user-tools']/a[@href='/']");
    @Name("Сообщение")
    SelenideElement messageList = $x("//ul[@class='messagelist']/li");
    @Name("Сообщение об ошибке")
    SelenideElement errorMessage = $(".errornote");
}

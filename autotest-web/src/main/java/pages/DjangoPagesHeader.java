package pages;

import com.codeborne.selenide.SelenideElement;
import ru.lanit.at.web.annotations.Name;
import ru.lanit.at.web.pagecontext.WebPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Name(value = "DjangoPagesHeader")
public class DjangoPagesHeader extends WebPage {

    @Name("Заголовок таблицы")
    private SelenideElement tableHeader = $x("//div[@id='content']/h1");

    @Name("Имя пользователя")
    private SelenideElement userName = $x("//div[@id='user-tools']/strong");
    @Name("username")
    private SelenideElement userTools = $x("//div[@id='user-tools']/strong");
    @Name("Выйти")
    private SelenideElement logout = $x("//div[@id='user-tools']/a[@href='/admin/logout/']");
    @Name("Изменить пароль")
    private SelenideElement changePassword = $x("//a[@href='/admin/password_change/']");
    @Name("Показать сайт")
    private SelenideElement viewSite = $x("//div[@id='user-tools']/a[@href='/']");
    @Name("Сообщение")
    private SelenideElement messageList = $x("//ul[@class='messagelist']/li");
    @Name("Сообщение об ошибке")
    private SelenideElement errorMessage = $(".errornote");
}

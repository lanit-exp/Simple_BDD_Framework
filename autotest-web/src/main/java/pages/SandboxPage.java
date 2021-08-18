package pages;

import com.codeborne.selenide.SelenideElement;
import ru.lanit.at.web.annotations.Name;
import ru.lanit.at.web.pagecontext.WebPage;

import static com.codeborne.selenide.Selenide.$x;

@Name(value = "Sandbox")
public class SandboxPage extends WebPage {

    @Name("Краткое описание проблемы")
    private SelenideElement shortDescriptionProblemField = $x("//label[contains(text(),'Краткое описание проблемы')]/..//input[@type='text']");

    @Name("Username")
    private SelenideElement userNameField = $x("//input[@name='username']");

    @Name("Password")
    private SelenideElement passwordField = $x("//input[@name='password']");

    @Name("Войти")
    private SelenideElement loginLink = $x("//a[contains(text(),'Войти')]");

    @Name("Вход")
    private SelenideElement enterButton = $x("//input[@value='Вход']");

    @Name("Таблица тикетов")
    private SelenideElement ticketTable = $x("//table[@id='ticketTable']");

    @Name("Создать тикет")
    private SelenideElement createTicket = $x("//*[text()='Создать тикет']");
}

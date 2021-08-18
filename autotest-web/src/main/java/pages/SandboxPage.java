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

    @Name("Новый Тикет")
    private SelenideElement newTicketSidebar = $x("//a[@href='/tickets/submit/']");

    @Name("Очередь")
    private SelenideElement queueDropdown = $x("//select[@name='queue']");

    @Name("Описание вашей проблемы")
    private SelenideElement descriptionProblemField = $x("//textarea[@id='id_body']");

    @Name("Приоритет")
    private SelenideElement priority = $x("//select[@id='id_priority']");

    @Name("Выполнить до")
    private SelenideElement doUntil = $x("//input[@name='due_date']");

    @Name("Ваш адрес электронной почты")
    private SelenideElement submitterEmail = $x("//input[@name='submitter_email']");

    @Name("Отправить тикет")
    private SelenideElement submitButton = $x("//button[@type='submit']");

}

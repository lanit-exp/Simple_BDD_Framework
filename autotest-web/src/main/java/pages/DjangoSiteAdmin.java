package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.lanit.at.web.annotations.Name;
import ru.lanit.at.web.pagecontext.WebPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

    @Name(value = "DjangoSiteAdmin")
    public class DjangoSiteAdmin extends WebPage {
        @Name("логин")
        private SelenideElement usernameField = $(By.id("id_username"));

        @Name("пароль")
        private SelenideElement passwordField = $(By.id("id_password"));

        @Name("Я желаю войти с админскими правами")
        private SelenideElement isAdminCheckbox = $(By.id("id_i_am_admin"));

        @Name("Я здесь впервые")
        private SelenideElement haveTokenCheckbox = $(By.id("id_i_have_no_token"));

        @Name("кнопка войти")
        private SelenideElement signinButton = $x("//*[@class='submit-row']");
    }


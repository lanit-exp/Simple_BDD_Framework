package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.lanit.at.web.annotations.Name;
import ru.lanit.at.web.pagecontext.WebPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Name(value = "DjangoPasswordChange")
public class DjangoPasswordChangePage extends WebPage {
    @Name("старый пароль")
    private SelenideElement oldPassword = $(By.id("id_old_password"));
    @Name("новый пароль")
    private SelenideElement newPassword = $(By.id("id_new_password1"));
    @Name("подтверждение нового пароля")
    private SelenideElement newPasswordConfirmation = $(By.id("id_new_password2"));
    @Name("изменить пароль")
    private SelenideElement changePassword = $x("//input[@type='submit']");
}

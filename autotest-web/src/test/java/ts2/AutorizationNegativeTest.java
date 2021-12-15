package ts2;

import hooks.WebHooks;
import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.lanit.at.web.pagecontext.PageManager;
import steps.WebCheckSteps;
import steps.WebSteps;

public class AutorizationNegativeTest extends WebHooks {

    private PageManager pageManager = new PageManager();
    private WebSteps webSteps = new WebSteps(pageManager);
    private WebCheckSteps webCheckSteps = new WebCheckSteps(pageManager);

    @DataProvider
    public Object[][] testDataAuthorizationNegativeWithoutFillingPassword() {
        return new Object[][]{
                {"admin"},
                {"project1_admin"},
                {"hr"},
                {"public"},
                {"just_employee"}
        };
    }

    @DataProvider
    public Object[][] testDataAuthorizationNegativeAdministratorInvalidPassword() {
        return new Object[][]{
                {"admin"},
                {"project1_admin"},
                {"hr"},
                {"public"},
        };
    }

    @DataProvider
    public Object[][] testDataAuthorizationNegativeAdministratorInvalidToken() {
        return new Object[][]{
                {"admin", "asdf", "111111"},
                {"project1_admin", "hrmhrm1234", "222222"},
                {"hr", "hrmhrm12345", "111111"},
                {"public", "hrmhrm123456", "222222"},
                {"admin", "asdf", "222222"},
                {"project1_admin", "hrmhrm1234", "111111"},
                {"hr", "hrmhrm12345", "222222"},
                {"public", "hrmhrm123456", "111111"},
        };
    }

    @DataProvider
    public Object[][] testDataAuthorizationNegativeAdministratorWithoutFillingToken() {
        return new Object[][]{
                {"admin", "asdf"},
                {"project1_admin", "hrmhrm1234"},
                {"hr", "hrmhrm12345"},
                {"public", "hrmhrm123456"},
        };
    }

    @Test
    @Description(value = "2.1 Авторизация без заполнения полей")
    public void authorizationNegativeWithoutFilling() {
        webSteps.openUrl();
        webSteps.setPage("DjangoAuthorization");
        webSteps.clickOnElement("войти");
        webCheckSteps.elementAttributeValue("логин", "validationMessage", "Заполните это поле.", 0);
    }

    @Test(dataProvider = "testDataAuthorizationNegativeWithoutFillingPassword")
    @Description(value = "2.2 Авторизация без заполнения поля \"Пароль\"")
    public void authorizationNegativeWithoutFillingPassword(String login) {
        webSteps.openUrl();
        webSteps.setPage("DjangoAuthorization");
        webSteps.fillField("логин", login);
        webSteps.clickOnElement("войти");
        webCheckSteps.elementAttributeValue("пароль", "validationMessage", "Заполните это поле.", 0);
    }

    @Test
    @Description(value = "2.3 Авторизация под ролью \"just_employee\" с невалидным заполнением поля \"Имя пользователя\"")
    public void authorizationNegativeInvalidJustEmployee() {
        webSteps.openUrl();
        webSteps.setPage("DjangoAuthorization");
        webCheckSteps.checkNoSelectedCheckbox("Я желаю войти с админскими правами");
        webSteps.fillField("логин", "qweфыв!");
        webSteps.fillField("пароль", "hrmhrm123");
        webSteps.clickOnElement("войти");
        webCheckSteps.textAppearOnThePage("Active Directory недоступен. Обратитесь к администратору");
    }

    @Test
    @Description(value = "2.4 Авторизация под администрирующими ролями с невалидным заполнением поля \"Имя пользователя\"")
    public void authorizationNegativeAdministratorInvalidLogin() {
        webSteps.openUrl();
        webSteps.setPage("DjangoAuthorization");
        webSteps.clickOnElement("Я желаю войти с админскими правами");
        webCheckSteps.checkSelectedCheckbox("Я желаю войти с админскими правами");
        webCheckSteps.checkNoSelectedCheckbox("Я здесь впервые");
        webSteps.fillField("логин", "qweфыв!");
        webSteps.fillField("пароль", "hrmhrm123");
        webSteps.fillField("токен", "111111");
        webSteps.clickOnElement("войти");
        webCheckSteps.textAppearOnThePage("Active Directory недоступен. Обратитесь к администратору");
    }

    @Test
    @Description(value = "2.5 Авторизация под администрирующими ролями впервые с невалидным заполнением поля \"Имя пользователя\"")
    public void authorizationNegativeAdministratorInvalidAdministratorFirst() {
        webSteps.openUrl();
        webSteps.setPage("DjangoAuthorization");
        webSteps.clickOnElement("Я желаю войти с админскими правами");
        webSteps.clickOnElement("Я здесь впервые");
        webSteps.fillField("логин", "qweфыв!");
        webSteps.fillField("пароль", "hrmhrm123");
        webSteps.clickOnElement("выслать инструкцию на почту");
        webSteps.clickOnElement("войти");
        webCheckSteps.textAppearOnThePage("Active Directory недоступен. Обратитесь к администратору");
    }

    @Test
    @Description(value = "2.6 Авторизация под администрирующими ролями впервые с невалидным заполнением поля \"Пароль\"")
    public void authorizationNegativeAdministratorInvalidPasswordFirst() {
        webSteps.openUrl();
        webSteps.setPage("DjangoAuthorization");
        webSteps.clickOnElement("Я желаю войти с админскими правами");
        webSteps.clickOnElement("Я здесь впервые");
        webSteps.fillField("логин", "qweфыв!");
        webSteps.fillField("пароль", "hrmhrm123");
        webSteps.clickOnElement("выслать инструкцию на почту");
        webSteps.clickOnElement("войти");
        webCheckSteps.textAppearOnThePage("Active Directory недоступен. Обратитесь к администратору");
    }

    @Test
    @Description(value = "2.7 Авторизация под ролью \"just_employee\" с невалидным заполнением поля \"Пароль\"")
    public void authorizationNegativeJustEmployeeInvalidPassword() {
        webSteps.openUrl();
        webSteps.setPage("DjangoAuthorization");
        webCheckSteps.checkNoSelectedCheckbox("Я желаю войти с админскими правами");
        webSteps.fillField("логин", "just_employee");
        webSteps.fillField("пароль", "qweasdzxc");
        webSteps.clickOnElement("войти");
        webCheckSteps.textAppearOnThePage("Данные неверные. Попробуйте ещё раз.");
    }

    @Test
    @Description(value = "2.8 Авторизация под ролью \"just_employee\" с активированными чек-боксами \"Я желаю войти с админскими правами\" и \"Я здесь впервые\"")
    public void authorizationNegativeJustEmployeeWithActiveCheckboxes() {
        webSteps.openUrl();
        webSteps.setPage("DjangoAuthorization");
        webSteps.clickOnElement("Я желаю войти с админскими правами");
        webSteps.clickOnElement("Я здесь впервые");
        webSteps.fillField("логин", "just_employee");
        webSteps.fillField("пароль", "hrmhrm123");
        webSteps.clickOnElement("выслать инструкцию на почту");
        webSteps.clickOnElement("войти");
        webCheckSteps.textAppearOnThePage("У этого пользователя нет админских прав.");
    }

    @Test
    @Description(value = "2.9 Авторизация под ролью \"just_employee\" с активированным чек-боксом \"Я желаю войти с админскими правами\"")
    public void authorizationNegativeJustEmployeeWithActiveCheckboxAdminNoToken() {
        webSteps.openUrl();
        webSteps.setPage("DjangoAuthorization");
        webSteps.clickOnElement("Я желаю войти с админскими правами");
        webCheckSteps.checkNoSelectedCheckbox("Я здесь впервые");
        webCheckSteps.checkAppearElement("токен");
        webSteps.fillField("логин", "just_employee");
        webSteps.fillField("пароль", "hrmhrm123");
        webSteps.clickOnElement("войти");
        webCheckSteps.elementAttributeValue("токен", "validationMessage", "Заполните это поле.", 0);
    }

    @Test
    @Description(value = "2.10 Авторизация под ролью \"just_employee\" с активированным чек-боксом \"Я желаю войти с админскими правами\" и заполненным полем \"OTP Token (from Google Authenticator)\"")
    public void authorizationNegativeJustEmployeeWithActiveCheckboxAdminWithToken() {
        webSteps.openUrl();
        webSteps.setPage("DjangoAuthorization");
        webSteps.clickOnElement("Я желаю войти с админскими правами");
        webCheckSteps.checkNoSelectedCheckbox("Я здесь впервые");
        webCheckSteps.checkAppearElement("токен");
        webSteps.fillField("логин", "just_employee");
        webSteps.fillField("пароль", "hrmhrm123");
        webSteps.fillField("токен", "111111");
        webSteps.clickOnElement("войти");
        webCheckSteps.textAppearOnThePage("У этого пользователя нет админских прав.");
    }

    @Test(dataProvider = "testDataAuthorizationNegativeAdministratorInvalidPassword")
    @Description(value = "2.11 Авторизация под администрирующими ролями с невалидным заполнением поля \"Пароль\"")
    public void authorizationNegativeAdministratorInvalidPassword(String login) {
        webSteps.openUrl();
        webSteps.setPage("DjangoAuthorization");
        webSteps.clickOnElement("Я желаю войти с админскими правами");
        webCheckSteps.checkNoSelectedCheckbox("Я здесь впервые");
        webSteps.fillField("логин", login);
        webSteps.fillField("пароль", "qweasdzxc");
        webSteps.fillField("токен", "111111");
        webSteps.clickOnElement("войти");
        webCheckSteps.textAppearOnThePage("Данные неверные. Попробуйте ещё раз.");
    }

    @Test(dataProvider = "testDataAuthorizationNegativeAdministratorInvalidToken")
    @Description(value = "2.12 Авторизация под администрирующими ролями с невалидным заполнением поля \"OTP Token (from Google Authenticator)\"")
    public void authorizationNegativeAdministratorInvalidToken(String login, String password, String token) {
        webSteps.openUrl();
        webSteps.setPage("DjangoAuthorization");
        webSteps.clickOnElement("Я желаю войти с админскими правами");
        webCheckSteps.checkNoSelectedCheckbox("Я здесь впервые");
        webSteps.fillField("логин", login);
        webSteps.fillField("пароль", password);
        webSteps.fillField("токен", token);
        webSteps.clickOnElement("войти");
        webCheckSteps.textAppearOnThePage("Данные неверные. Попробуйте ещё раз.");
    }

    @Test(dataProvider = "testDataAuthorizationNegativeAdministratorWithoutFillingToken")
    @Description(value = "2.13 под администрирующими ролями без заполнения поля \"OTP Token (from Google Authenticator)\"")
    public void authorizationNegativeAdministratorWithoutFillingToken(String login, String password) {
        webSteps.openUrl();
        webSteps.setPage("DjangoAuthorization");
        webSteps.clickOnElement("Я желаю войти с админскими правами");
        webCheckSteps.checkNoSelectedCheckbox("Я здесь впервые");
        webSteps.fillField("логин", login);
        webSteps.fillField("пароль", password);
        webCheckSteps.checkAppearElement("токен");
        webSteps.clickOnElement("войти");
        webCheckSteps.elementAttributeValue("токен", "validationMessage", "Заполните это поле.", 0);
    }
}

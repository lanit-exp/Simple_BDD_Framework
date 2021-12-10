package ts2;

import hooks.WebHooks;
import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.lanit.at.web.pagecontext.PageManager;
import steps.AuthorizationCheckSteps;
import steps.AuthorizationSteps;
import steps.WebActionSteps;
import steps.WebCheckSteps;

public class AutorizationNegativeTest extends WebHooks{

    private PageManager pageManager = new PageManager();
    private AuthorizationSteps authorizationSteps = new AuthorizationSteps(pageManager);
    private WebCheckSteps webCheckSteps = new WebCheckSteps(pageManager);
    private WebActionSteps webActionSteps= new WebActionSteps(pageManager);
    private AuthorizationCheckSteps authorizationCheckSteps = new AuthorizationCheckSteps(pageManager);


    @DataProvider
    public Object[][] testDataAutorizationNegativeWithoutFillingPassword() {
        return new Object[][]{
                {"admin"},
                {"project1_admin"},
                {"hr"},
                {"public"},
                {"just_employee"}
        };
    }



    @Test
    @Description(value = "2.1 Негативная авторизация без заполнения полей")
    public void autorizationNegativeWithoutFilling(){

        authorizationSteps.openUrl(); // открываем сайт
        authorizationSteps.setPage("DjangoAuthorization"); // инициализируем страницу
        authorizationSteps.clickSignInButton("войти"); // нажимаем на кнопку войти
        webCheckSteps.elementAttributeValue("логин","validationMessage","Заполните это поле.",0); // проверяем информационное сообщение
    }



    @Test(dataProvider = "testDataAutorizationNegativeWithoutFillingPassword" )
    @Description(value = "2.2 Негативная авторизация с некорректным паролем")
    public void autorizationNegativeWithoutFillingPassword(String login){
        AuthorizationSteps authorizationSteps = new AuthorizationSteps(pageManager);
        WebActionSteps webActionSteps= new WebActionSteps(pageManager);
        WebCheckSteps webCheckSteps = new WebCheckSteps(pageManager);

        authorizationSteps.openUrl(); // открываем сайт
        authorizationSteps.setPage("DjangoAuthorization");  // инициализируем страницу
        webActionSteps.fillTheField("логин",login); // заполняем поле логин
        authorizationSteps.clickSignInButton("войти"); // нажимаем на кнопку войти
        webCheckSteps.elementAttributeValue("пароль","validationMessage","Заполните это поле.",0); // проверяем информационное сообщение
    }

    @Test
    @Description(value = "2.3 Негативная авторизация под ролью \"just_employee\"")
    public void autorizationNegativeNovalidNustEmployee(){


        authorizationSteps.openUrl(); // открываем сайт
        authorizationSteps.setPage("DjangoAuthorization"); // инициализируем страницу
        authorizationCheckSteps.checkNoSelectedCheckbox("Я желаю войти с админскими правами"); // кликаем на чекбокс
        webActionSteps.fillTheField("логин","qweфыв!"); // заполняем поле логин
        webActionSteps.fillTheField("пароль","hrmhrm123"); // заполняем поле пароль
        authorizationSteps.clickSignInButton("войти");  // нажимаем на кнопку войти
        webCheckSteps.textAppearOnThePage("Active Directory недоступен. Обратитесь к администратору"); // проверяем информационное сообщение
    }

    @Test
    @Description(value = "2.4 Негативная авторизация под администрирующими ролями с невалидным логином")
    public void autorizationNegativeNovalidAdministrator(){


        authorizationSteps.openUrl(); // открываем сайт
        authorizationSteps.setPage("DjangoAuthorization"); // инициализируем страницу
        webActionSteps.clickOnButton("Я желаю войти с админскими правами"); // кликаем на чекбокс
        authorizationCheckSteps.checkSelectedCheckbox("Я желаю войти с админскими правами"); // проверяем чекбокс
        authorizationCheckSteps.checkNoSelectedCheckbox("Я здесь впервые"); // проверяем чекбокс
        webActionSteps.fillTheField("логин","qweфыв!"); // заполняем поле логин
        webActionSteps.fillTheField("пароль","hrmhrm123");  // заполняем поле пароль
        webActionSteps.fillTheField("токен","111111");  // заполняем поле токен
        authorizationSteps.clickSignInButton("войти"); // нажимаем на кнопку войти
        webCheckSteps.textAppearOnThePage("Active Directory недоступен. Обратитесь к администратору"); // проверяем информационное сообщение
    }

    @Test
    @Description(value = "2.5 Негативная авторизация под администрирующими ролями впервые")
    public void autorizationNegativeNovalidAdministratorFirst(){


        authorizationSteps.openUrl(); // открываем сайт
        authorizationSteps.setPage("DjangoAuthorization"); // инициализируем страницу
        webActionSteps.clickOnButton("Я желаю войти с админскими правами"); // кликаем на чекбокс
        webActionSteps.clickOnButton("Я здесь впервые"); // кликаем на чекбокс
        webActionSteps.fillTheField("логин","qweфыв!"); // заполняем поле логин
        webActionSteps.fillTheField("пароль","hrmhrm123");  // заполняем поле пароль
        webActionSteps.clickOnButton("выслать инструкцию на почту"); // кликаем на элемент "выслать инструкцию на почту"
        authorizationSteps.clickSignInButton("войти"); // нажимаем на кнопку войти
        webCheckSteps.textAppearOnThePage("Active Directory недоступен. Обратитесь к администратору"); // проверяем информационное сообщение
    }

    @Test
    @Description(value = "2.6 Негативная авторизация под администрирующими ролями с невалидным паролем")
    public void autorizationNegativeNovalidAdministrator1(){


        authorizationSteps.openUrl(); // открываем сайт
        authorizationSteps.setPage("DjangoAuthorization"); // инициализируем страницу
        webActionSteps.clickOnButton("Я желаю войти с админскими правами"); // кликаем на чекбокс
        webActionSteps.clickOnButton("Я здесь впервые"); // кликаем на чекбокс
        webActionSteps.fillTheField("логин","qweфыв!"); // заполняем поле логин
        webActionSteps.fillTheField("пароль","hrmhrm123");  // заполняем поле пароль
        webActionSteps.clickOnButton("выслать инструкцию на почту"); // кликаем на элемент "выслать инструкцию на почту"
        authorizationSteps.clickSignInButton("войти"); // нажимаем на кнопку войти
        webCheckSteps.textAppearOnThePage("Active Directory недоступен. Обратитесь к администратору"); // проверяем информационное сообщение
    }





}

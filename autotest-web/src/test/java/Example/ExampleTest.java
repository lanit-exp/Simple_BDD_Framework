package Example;

import hooks.WebHooks;
import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.lanit.at.web.pagecontext.PageManager;
import steps.AuthorizationSteps;
import steps.WebActionSteps;
import steps.WebCheckSteps;


public class ExampleTest extends WebHooks { //  Наследуемся от WebHooks


    private PageManager pageManager = new PageManager(); // Создаем экземпляр PageManager



    @DataProvider
    public Object[][] testDataAutorizationNegativeWithoutFillingPassword() { // DataProvider, для тестовых данных, если нужен
        return new Object[][]{
                {"admin"},
                {"project1_admin"},
                {"hr"},
                {"public"},
                {"just_employee"}
        };
    }


    @Test(dataProvider = "testDataAutorizationNegativeWithoutFillingPassword" )
    @Description(value = "2.2 Негативная авторизация с пустым паролем")
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



}

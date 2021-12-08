package tk2_1;

import hooks.WebHooks;
import io.qameta.allure.Description;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.lanit.at.web.pagecontext.PageManager;
import steps.AuthorizationSteps;
import steps.WebActionSteps;
import steps.WebCheckSteps;

public class AutorizationNegativeWithoutFillingTest {

    private PageManager pageManager = new PageManager();

    @BeforeTest
    public void setupTest(){
        new WebHooks().setup();
    }


    @AfterTest
    public void closeTest(){
        new WebHooks().close();
    }

    @Test
    @Description("Авторизация без заполнения полей")
    public void autorizationNegativeWithoutFilling(){
        AuthorizationSteps authorizationSteps = new AuthorizationSteps(pageManager);
        authorizationSteps.openUrl();// открываем сайт
        authorizationSteps.setPage("DjangoAuthorization"); // Инициализируем страницу
//        WebActionSteps webActionSteps = new WebActionSteps(new PageManager());
        authorizationSteps.clickSignInButton("войти"); // кликаем на кнопку "войти"
        WebCheckSteps webCheckSteps = new WebCheckSteps(pageManager);
        webCheckSteps.elementAttributeValue("логин","validationMessage","Заполните это поле.",0);
    }


}

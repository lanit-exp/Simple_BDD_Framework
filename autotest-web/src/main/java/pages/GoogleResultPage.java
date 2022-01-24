package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.lanit.at.web.annotations.Name;
import ru.lanit.at.web.pagecontext.WebPage;

import static com.codeborne.selenide.Selenide.$;

@Name(value = "Google страница результатов")
public class GoogleResultPage extends WebPage {

    @Name("виджет погоды")
    private SelenideElement searchField = $(By.id("wob_wc"));
}
package pages.employee;

import com.codeborne.selenide.SelenideElement;
import pages.DjangoPagesHeader;
import ru.lanit.at.web.annotations.Name;

import static com.codeborne.selenide.Selenide.$x;

@Name(value = "DjangoReturnEmployee")
public class DjangoReturnEmployeePage extends DjangoPagesHeader {

    @Name("Сохранить")
    private SelenideElement save = $x("//input[@name='_save']");

}


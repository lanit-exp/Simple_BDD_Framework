package pages.employee;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.DjangoPagesHeader;
import ru.lanit.at.web.annotations.Name;

import static com.codeborne.selenide.Selenide.*;

@Name(value = "DjangoRestoreEmployee")
public class DjangoRestoreEmployeePage extends DjangoPagesHeader {

    @Name("Дата и время")
    private ElementsCollection dateAndTime = $$x("//th[@scope='row']/a");

    @Name("Сотрудник")
    private ElementsCollection employee = $$("#change-history tbody tr td");
}


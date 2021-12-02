package pages.employee;

import com.codeborne.selenide.ElementsCollection;
import ru.lanit.at.web.annotations.Name;
import ru.lanit.at.web.pagecontext.WebPage;

import static com.codeborne.selenide.Selenide.*;

@Name(value = "DjangoRestoreEmployee")
public class DjangoRestoreEmployeePage extends WebPage {

    @Name("Дата и время")
    private ElementsCollection dateAndTime = $$x("//th[@scope='row']/a");

    @Name("Сотрудник")
    private ElementsCollection employee = $$("#change-history tbody tr td");
}


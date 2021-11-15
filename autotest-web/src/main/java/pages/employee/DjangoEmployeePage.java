package pages.employee;

import com.codeborne.selenide.ElementsCollection;
import pages.DjangoPagesHeader;
import ru.lanit.at.web.annotations.Name;

import static com.codeborne.selenide.Selenide.*;

@Name(value = "DjangoEmployee")
public class DjangoEmployeePage extends DjangoPagesHeader {
    @Name("ФИО")
    private ElementsCollection fio = $$x("//th[@class='field-full_name']/a");
}


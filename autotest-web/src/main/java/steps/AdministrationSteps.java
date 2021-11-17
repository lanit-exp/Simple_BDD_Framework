package steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.lanit.at.web.pagecontext.PageManager;

public class AdministrationSteps {

    private PageManager pageManager;
    private final Logger LOGGER = LoggerFactory.getLogger(AdministrationSteps.class);

    public AdministrationSteps(PageManager pageManager) {
        this.pageManager = pageManager;
    }
}

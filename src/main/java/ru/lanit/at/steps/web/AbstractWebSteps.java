package ru.lanit.at.steps.web;

import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.lanit.at.utils.ContextHolder;
import ru.lanit.at.utils.web.pagecontext.Environment;
import ru.lanit.at.utils.web.pagecontext.PageManager;
import ru.lanit.at.utils.web.pagecontext.WebPage;
import ru.lanit.at.utils.web.properties.Configurations;

import java.util.Map;


public abstract class AbstractWebSteps {
    protected final Configurations configurations;
    protected Logger LOGGER = LogManager.getLogger(this.getClass());
    protected PageManager pageManager;


    public AbstractWebSteps(PageManager pageManager) {
        this.pageManager = pageManager;
        configurations = ConfigFactory.create(Configurations.class, System.getProperties(),
                System.getenv());
    }

    protected Map<String, Object> getStorage() {
        return ContextHolder.asMap();
    }

    protected void saveValueInStorage(String key, Object value) {
        getStorage().put(key, value);
    }

    protected WebPage getPage(String name) {
        WebPage page = Environment.getPage(name);
        pageManager.setCurrentPage(page);
        return page;
    }

    protected <T extends WebPage> T getPage(Class<T> c) {
        WebPage page = Environment.getPage(c);
        pageManager.setCurrentPage(page);
        return (T) page;
    }
}

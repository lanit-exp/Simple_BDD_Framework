package ru.lanit.at.utils.web.pagecontext;

import com.codeborne.selenide.Selenide;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Предназначен для хранения страниц, используемых при прогоне тестов
 */
public final class PageCache {

    /**
     * Страницы, на которых будет производится тестирование < Имя, Страница >
     */
    private Map<String, Class<? extends WebPage>> pages;

    public PageCache() {
        pages = Maps.newHashMap();
    }

    /**
     * Получение страницы из "pages" по имени
     */
    public WebPage get(String pageName) {
        return Selenide.page(getPageFromPagesByName(pageName)).initialize();
    }

    /**
     * Получение страницы по классу
     */
    @SuppressWarnings("unchecked")
    public <T extends WebPage> T get(Class<T> clazz, String name) {
        WebPage webPage = Selenide.page(getPageFromPagesByName(name)).initialize();
        if (!clazz.isInstance(webPage)) {
            throw new IllegalStateException(name + " page is not a instance of " + clazz + ". Named page is a " + webPage);
        }
        return (T) webPage;
    }

    private Map<String, Class<? extends WebPage>> getPageMapInstanceInternal() {
        return pages;
    }

    private Class<? extends WebPage> getPageFromPagesByName(String pageName) throws IllegalArgumentException {
        Class<? extends WebPage> page = getPageMapInstanceInternal().get(pageName);
        if (page == null) {
            throw new IllegalArgumentException("Страница с именем '" + pageName + "' не задекларирована");
        }
        return page;
    }

    public void put(String pageName, Class<? extends WebPage> page) {
        if (page == null) {
            throw new IllegalArgumentException("Была передана пустая страница");
        }
        pages.put(pageName, page);
    }
}
package ru.lanit.at.utils.web.pagecontext;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Используется для хранения кеша страниц и драйвера
 */
public class Environment {

    private static final Logger LOGGER = LoggerFactory.getLogger(Environment.class);


    /**
     * Список веб-страниц, заданных пользователем, доступных для использования в сценарии
     */
    private static PageCache pages;

    /**
     * Метод ищет классы, аннотированные "ru.lanit.ru.lanit.at.utils.web.annotations.Name",
     * добавляя ссылки на эти классы в поле "pages"
     *
     * @param packageName наименование пакета где лежат файлы с описанием страниц
     */
    @SuppressWarnings("unchecked")
    public static void initPages(String packageName) {
        pages = new PageCache();
        ru.lanit.at.utils.reflections.ReflectionUtil
                .getPagesAnnotatedWith(packageName, ru.lanit.at.utils.web.annotations.Name.class)
                .stream()
                .map(it -> {
                    if (WebPage.class.isAssignableFrom(it)) {
                        return (Class<? extends WebPage>) it;
                    } else {
                        throw new IllegalStateException("Класс " + it.getName() + " должен наследоваться от WebPage");
                    }
                })
                .forEach(clazz -> pages.put(getClassAnnotationValue(clazz), clazz));
    }

    /**
     * Вспомогательный метод, получает значение аннотации "ru.absolut.annotations.Name" для класса
     */
    private static String getClassAnnotationValue(Class<?> c) {
        return Arrays
                .stream(c.getAnnotationsByType(ru.lanit.at.utils.web.annotations.Name.class))
                .findFirst()
                .map(ru.lanit.at.utils.web.annotations.Name::value)
                .orElseThrow(() -> new AssertionError("Не найдены аннотации Page.Name в классe " + c.getName()));
    }

    public static WebPage getPage(String name) {
        return pages.get(name);
    }

    public static WebPage getPage(Class<?> c) {
        return getPage(getClassAnnotationValue(c));
    }


}
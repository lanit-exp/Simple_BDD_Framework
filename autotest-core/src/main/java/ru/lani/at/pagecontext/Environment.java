package ru.lani.at.pagecontext;

import ru.lani.at.annotations.Name;
import ru.lani.at.reflections.ReflectionUtil;

import java.util.Arrays;

/**
 * Используется для хранения кеша страниц и драйвера
 */
public class Environment {

    /**
     * Список веб-страниц, заданных пользователем, доступных для использования в сценарии
     */
    private static PageCache pages;

    /**
     * Метод ищет классы, аннотированные "ru.absolut.annotations.Name",
     * добавляя ссылки на эти классы в поле "pages"
     *
     * @param packageName наименование пакета где лежат файлы с описанием страниц
     */
    @SuppressWarnings("unchecked")
    public static void initPages(String packageName) {
        pages = new PageCache();
        ReflectionUtil
                .getPagesAnnotatedWith(packageName, Name.class)
                .stream()
                .map(it -> {
                    if (WebPage.class.isAssignableFrom(it)) {
                        return (Class<? extends WebPage>) it;
                    } else {
                        throw new IllegalStateException("Класс " + it.getName() + " должен наследоваться от PageCore");
                    }
                })
                .forEach(clazz -> pages.put(getClassAnnotationValue(clazz), clazz));
    }

    /**
     * Вспомогательный метод, получает значение аннотации "ru.absolut.annotations.Name" для класса
     */
    private static String getClassAnnotationValue(Class<?> c) {
        return Arrays
                .stream(c.getAnnotationsByType(Name.class))
                .findFirst()
                .map(Name::value)
                .orElseThrow(() -> new AssertionError("Не найдены аннотации Page.Name в классe " + c.getName()));
    }

    public static WebPage getPage(String name) {
        return pages.get(name);
    }
}
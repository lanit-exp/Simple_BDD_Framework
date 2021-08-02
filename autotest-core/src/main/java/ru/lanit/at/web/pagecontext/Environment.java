package ru.lanit.at.web.pagecontext;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.lanit.at.reflections.ReflectionUtil;
import ru.lanit.at.web.annotations.Name;

import java.util.Arrays;

/**
 * Используется для хранения кеша страниц и драйвера
 */
public class Environment {

    private static final Logger LOGGER = LoggerFactory.getLogger(Environment.class);
    private static final ThreadLocal<WebDriver> THREAD_DRIVER = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return THREAD_DRIVER.get();
    }

    public static void setThreadDriver(WebDriver threadDriver) {
        THREAD_DRIVER.set(threadDriver);
    }

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
                .stream(c.getAnnotationsByType(Name.class))
                .findFirst()
                .map(Name::value)
                .orElseThrow(() -> new AssertionError("Не найдены аннотации Page.Name в классe " + c.getName()));
    }

    public static WebPage getPage(String name) {
        return pages.get(name);
    }

    public static void demountDriver() {
        if (getDriver() != null) {
            getDriver().quit();
        }
        THREAD_DRIVER.set(null);
        LOGGER.info("close webdriver thread: {}", Thread.currentThread().getId());
    }
}
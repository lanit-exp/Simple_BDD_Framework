package ru.lani.at.pagecontext;

/**
 * Класс, который хранит текущую страницу теста
 */
public class PageManager {

    public PageManager() {
    }

    private WebPage currentWebPage;

    /**
     * Возвращает текущую страницу, на которой в текущий момент производится тестирование
     */
    public WebPage getCurrentWebPage() {
        if (currentWebPage == null) {
            throw new IllegalStateException("Текущая страница не задана");
        }
        return currentWebPage.initialize();
    }

    /**
     * Задает текущую страницу по ее имени
     */
    public void setCurrentWebPage(WebPage webPage) {
        this.currentWebPage = webPage;
    }

}
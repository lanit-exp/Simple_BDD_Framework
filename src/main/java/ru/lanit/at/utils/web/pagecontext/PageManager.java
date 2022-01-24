package ru.lanit.at.utils.web.pagecontext;

/**
 * Класс, который хранит текущую страницу теста
 */
public class PageManager {

    private WebPage currentPage;

    public PageManager() {
    }

    /**
     * Возвращает текущую страницу, на которой в текущий момент производится тестирование
     */
    public WebPage getCurrentPage() {
        if (currentPage == null) {
            throw new IllegalStateException("Текущая страница не задана");
        }
        return currentPage.initialize();
    }

    /**
     * Задает текущую страницу по ее имени
     */
    public void setCurrentPage(WebPage webPage) {
        this.currentPage = webPage;
    }

}
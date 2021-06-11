package steps;

import actions.WebActions;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import ru.lani.at.pagecontext.Environment;
import ru.lani.at.pagecontext.PageManager;
import ru.lani.at.pagecontext.WebPage;

public class WindowSteps {

    private PageManager pageManager;

    public WindowSteps(PageManager manager) {
        this.pageManager = manager;
    }

    /**
     * открывает страницу по ссылке
     *
     * @param url url
     */
    @Если("открыть url {string}")
    public void open(String url) {
        Selenide.open(url);
    }

    /**
     * открывает новую вкладку в браузере с url и переключается на нее
     *
     * @param url url
     */
    @И("открыть новую вкладку с url {string}")
    public void openNewTab(String url) {
        WebActions.openUrlOnNewTab(url);
    }

    /**
     * если вкладок 2, то переключится на следующую вкладку.
     * <br/>по факту переключается на последнюю вкладку
     */
    @И("переключиться на следующую вкладку")
    public void switchNextTab() {
        WebActions.switchToNextTab(null);
    }

    /**
     * переключается на вкладку по порядковому номеру
     *
     * @param number порядковый номер вкладки в браузере
     */
    @И("пеерключиться на вкладку по порядковому номеру {int}")
    public void switchNextTabByNumber(int number) {
        WebActions.switchToNextTab(number);
    }

    /**
     * закрывает текущую вкладку и переходит на предыдущую (последняя вкладка в наборе)
     */
    @И("закрыть текущую вкладку и перейти на предыдущую")
    public void closeTabAndSwitchTab() {
        WebActions.closeCurrentTabAndSwitchToPrevious();
    }

    /**
     * закрывает страницу
     */
    @Если("закрыть страницу")
    public void closeDriver() {
        WebDriverRunner.getWebDriver().close();
    }

    /**
     * действие обозначает что мы находимся на определенной странице
     * для работы с описанными элементами в пейдже
     *
     * @param pageName наименование страницы
     */
    @Если("пользователь на странице {string}")
    @Когда("инициализация страницы {string}")
    public void setPage(String pageName) {
        WebPage page = Environment.getPage(pageName);
        pageManager.setCurrentPage(page);
    }
}

## Как начать писать WEB автотесты
## 1.  Page Objects
В модуле ***autotest-web*** в директории ***src/main/java/pages*** находятся классы ***PageObjects***<br/>
**1.1** Каждый ***PageObject*** должен наследоваться от класса ***WebPage***<br/>
**1.2** Над классом необходимо проставить аннотацию *@Name(**value** = "<имя страницы>")*<br/>
Пример:<br/>
```java
@Name(value = "Google")
public class GooglePage extends WebPage {
    
    @Name("поле поиска") 
    private SelenideElement searchField = $x("//input[@name='q']");

    @Name("результаты поиска")
    private ElementCollections results = $x(xpath);
}
```
## 2. Степы
**2.1** В классе со степами необходимо объявить поле PageManager следующим образом:<br/>
* ссылка ***pageManager*** хранит в себе инициализированный контекст текущей страницы<br/>
```java
public class MySteps {
    private PageManager pageManager;
		
    public MySteps(PageManager pageManager) {  
	    this.pageManager = pageManager;  
    }
    // steps
}
```
Более подробно о подходе можно ознакомиться по ссылке [Cucumber PicoContainer](https://cucumber.io/docs/cucumber/state/) <br/>
**2.2** Пример инициализации страницы:<br/>
**pageName** - это value аннотации **Name** класса ***PageObject*** - в нашем примере *"Google"*
```java
public void setPage(String pageName) {
    WebPage page = Environment.getPage(pageName);
    pageManager.setCurrentPage(page);
}
```
**2.3** Теперь страница проинициализированна и получить доступ к элементам можно по его имени(value)<br/>
```java
@Если("кликнуть на элемент {string}")
public void clickOnElement(String elementName) {
    SelenideElement element = pageManager
                        .getCurrentPage()
                        .getElement(elementName);
    element.shouldBe(visible).click();
}
```

## 3. Тесты

```gherkin
#language:ru
Функционал: Поиск гугл
  Сценарий: Открытие страницы google.com, ввод значения в поис

    * открыть url "https://www.google.ru/"
    * инициализация страницы "Google"
    * ввести в поле "поле поиска" значение "Погода в Москве"
    * на странице имеется элемент "результаты поиска"
    * кликнуть на элемент "кнопка поиска"
    * инициализация страницы "страница результатов поиска"
    * на странице присутствует текст "Погода в Москве"
```
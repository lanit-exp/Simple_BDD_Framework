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


## Как начать писать API тесты
### Принцип написания тестов похож на подход создания и отправки запроса в Postman
*Шаг 1. Конфигурируем запрос с помощью шага*
```gherkin
* создать запрос  
  | method | path | body | url |
```
Если какой то из столбцов не указан в данном шаге, то он не учитывается в запросе
Например:
```gherkin
* создать запрос  
  | method | path  | body            |  
  | POST   | /user | createUser.json |
ИЛИ
* создать запрос  
  | method | path  |      body        |  
  | POST   | /user | {<тело запроса>} |
ИЛИ
* создать запрос  
  | method |                  url                           |   
  | GET    | https://petstore.swagger.io/v2/user/<username> |
```
* Можно указать ***basePath*** через одноименную системную переменную или в файле конфигурации ***configuration.properties***. Тогда вместо столбца url можно указывать просто path. И наоборот, если указать столбец url с полным url хоста и path то basePath не учитывается, даже если указан с системных переменных.
* Тело запроса -  в качестве тела можно передать в таблицу, как просто текст, так и название файла ***json***, которое будет лежать по пути ***autotest-rest/src/test/resources/json***

*Шаг 2. Добавление Headers и Query*
```gnerkin
* добавить header  
  | Content-Type | application/json |
```
```gnerkin
* добавить query параметры  
  | city | Moscow |
```
*Шаг 3. Отправка запроса*
```gnerkin
* отправить запрос
```
*Шаг 4. Проверка ответа*
```gnerkin
* статус код 200
```
Если необходимо проверить тело ответа то данные можно вытащить с помощью jsonpath
```gnerkin
* извлечь данные  
  | user_id | $.message |
```
Проверить извлеченные данные можно с помощью шага:
```gnerkin
* сравнить значения  
| ${user_id} | != | null |
ИЛИ
| ${user_id} | == | 1234567890 |
ИЛИ
| ${user_id} | > | 0 |
ИЛИ
| ${user_id} | < | 100 |
ИЛИ
| ${user_id} | содержит | qwerty123 |
```
### Иная информация.
С помощью следующего шага
```gherkin
* снегерировать переменные
   | id         | 0                 |
   | username   | EEEEEEEE          |
   | firstName  | EEEEEEEE          |
   | lastName   | EEEEEEEE          |
   | email      | EEEEEEE@EEEDDD.EE |
   | password   | DDDEEEDDDEEE      |
```
**R** - случайная русская буква<br/>
**E** - случайная английская буква<br/>
**D** - случайное число<br/>
Другие символы с строке игнорятся и остаются неизменяемыми
Сгенерированные значения хранятся в контексте теста. Их можно подставлять в запросы, тела запросов. Достать их можно используя синтаксис ***${username}***
По итогу прогонов можно сгенерить Allure отчет
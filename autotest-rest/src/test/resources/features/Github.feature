#language:ru
@test

Функционал: Selenide github test
  - Выполнение запроса в репозиторий selenide
  - Проверка что статус-код = 200
  - Извлечение данных из тела ответа по jsonpath
  - Проверка извлеченных данных

  Сценарий: Выполнение GET запроса в репозиторий selenide

    * создать запрос
      | method | url                                        |
      | GET    | https://api.github.com/orgs/selenide/repos |
    * добавить header
      | Accept | application/vnd.github.v3+json |
    * отправить запрос
    * статус код 200
    * извлечь данные
      | name     | $[?(@.full_name=='selenide/selenide')].name     |
      | id       | $[?(@.full_name=='selenide/selenide')].id       |
      | language | $[?(@.full_name=='selenide/selenide')].language |
      | homepage | $[?(@.full_name=='selenide/selenide')].homepage |
      | size     | $[?(@.full_name=='selenide/selenide')].size     |

    * сравнить значения
      | ${name}     | == | selenide            |
      | ${id}       | != | null                |
      | ${language} | == | Java                |
      | ${homepage} | == | http://selenide.org |
      | ${size}     | >  | 0                   |
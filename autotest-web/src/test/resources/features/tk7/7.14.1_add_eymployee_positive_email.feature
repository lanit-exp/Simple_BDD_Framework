#language:ru
@DjangoAddEmployee
Функционал: Проверка заполняемости поля "Корпоративная почта"
  Предыстория:
    Дано открыть "http://178.154.246.238:58082/"
    Тогда инициализация страницы "DjangoAuthorization"

    Если нажать на чекбокс "Я желаю войти с админскими правами"
    То заполнить поле "логин" значением "admin"
    И заполнить поле "пароль" значением "asdf"

    Если на странице имеется элемент "токен"
    Тогда ввести "токен" для пользователя "admin" с паролем "asdf"

    Если на странице имеется элемент "войти"
    Тогда кликнуть на элемент "войти"
    И переход на страницу "DjangoAdministration"

    Если кликнуть на элемент "Сотрудники"
    То инициализация страницы "DjangoEmployee"

    Если кликнуть на элемент "Добавить сотрудник"
    То инициализация страницы "DjangoEmployeeChange"

  Сценарий:На текущей странице в поле 'Корпоративная почта' ввести любое значение в формате 'ХХХХХ@XXXXX.XXX'

    Дано инициализация страницы "DjangoEmployeeChange"
    Если на странице имеется элемент "Email"
    Тогда ввести в поле "Email" значение "dfghh@gmail.mom"




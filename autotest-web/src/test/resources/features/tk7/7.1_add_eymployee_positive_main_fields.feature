#language:ru
@DjangoAddEmployee
Функционал: Проверка заполняемости ключевых полей блока и создания нового сотрудника
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

  Сценарий: Открытие страницы Django Administration, авторизация от лица админ,
  кликнуть на элемент "Сотрудники", на открывшейся странице кликнуть на элемент "Добавить сотрудник"
  заполнить ключевые поля, кликнуть на "Сохранить", проверить, что сотрудник добавился

    Дано инициализация страницы "DjangoAdministration"
    Если кликнуть на элемент "Сотрудники"
    То инициализация страницы "DjangoEmployee"

    Если кликнуть на элемент "Добавить сотрудник"
    То инициализация страницы "DjangoEmployeeChange"
    
    Если ввести в поле "Фамилия" значение "Иванов"
    И ввести в поле "Имя" значение "Иван"
    И ввести в поле "Отчество" значение "Иванович"
    И кликнуть на элемент "Пол"
    И кликнуть на элемент по тексту "Male"

    Если нажать на "Сохранить"
    То инициализация страницы "DjangoEmployee"
    И поле "Сообщение о успешном редактирование" инфоблок с текстом "The Сотрудник “Иванов Иван” was added successfully." присутствует


#language:ru
@Test
Функционал: Негативная авторизация

  Структура сценария: Негативня авторизация без ввода пароля
    Дано открыть url "http://178.154.246.238:58082/"
    Когда инициализация страницы "DjangoSiteAdmin"
    Тогда ввести в поле "логин" значение 'логин'
    И кликнуть на элемент "кнопка войти"
    И проверить что "пароль" есть  атрибут "validationMessage" с значением "Заполните это поле." подождав 0
    Примеры:
      | логин          |
      | admin          |
      | project1_admin |
      | hr             |
      | public         |
      | just_employee  |



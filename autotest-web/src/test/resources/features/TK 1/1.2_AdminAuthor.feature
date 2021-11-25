#language:ru
@djangoAdmin
Функционал: Проверка авторизации на странице авторизации от имени "admin"

  Сценарий: Открытие страницы http://178.154.246.238:58082/, активация поля "Я желаю войти с админскими
  правами", ввод значения "admin" в поле логина, ввод значения "asdf" в поле пароля, получение токена с
  помощью api запроса, ввод токена в поле токена, авторизация с помощью кнопки "войти", проверка
  отсутствия сообщения об ошибке, проверка нахождения на странице "Администрирование Django", проверка
  присутствия на странице значения "ДОБРО ПОЖАЛОВАТЬ, ADMIN"

    Дано открыть "http://178.154.246.238:58082/"
    Тогда инициализация страницы "DjangoAuthorization"
    Если чекбокс "Я желаю войти с админскими правами" отображается и не выбран
    Тогда нажать на чекбокс "Я желаю войти с админскими правами"
    Если чекбокс "Я здесь впервые" отображается и не выбран
    Тогда заполнить поле "логин" значением "admin"
    И заполнить поле "пароль" значением "asdf"
    Если поле ввода "токен" отображается
    Тогда ввести 'токен' для пользователя "admin" с паролем "asdf"
    Если на странице имеется элемент "войти"
    Тогда нажать на "войти"
    Если на странице отсутствует текст "Сообщение об ошибке"
    Тогда переход на страницу "DjangoPagesHeader"
    И инициализация страницы "DjangoPagesHeader"
    Если на странице в поле Добро пожаловать, имеется элемент "admin"
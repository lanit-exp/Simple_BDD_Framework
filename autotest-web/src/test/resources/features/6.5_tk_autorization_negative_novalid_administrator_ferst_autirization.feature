#language:ru
@Test
Функционал: Авторизация под администрирующими ролями впервые

    Сценарий: Проверка авторизации под администрирующими ролями впервые с невалидным заполнением поля "Имя пользователя"
    Дано открыть url "http://178.154.246.238:58082/"
    Когда инициализация страницы "DjangoAuthorization"
    Тогда кликнуть на элемент "Я желаю войти с админскими правами"
    И кликнуть на элемент "Я здесь впервые"
    Тогда ввести в поле "логин" значение "qweфыв!"
    И ввести в поле "пароль" значение "hrmhrm123"
    И кликнуть на элемент "выслать инструкцию на почту"
    Тогда кликнуть на элемент "войти"
    Если на странице присутствует текст "Active Directory недоступен. Обратитесь к администратору"



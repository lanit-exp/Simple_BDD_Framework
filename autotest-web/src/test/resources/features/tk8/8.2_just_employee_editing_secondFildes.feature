#language:ru
@djangoHr
Функционал: Проверка корректности изменений второстепенных полей сотрудника под ролью just_employee

  Предыстория: Иницилизация и авторизация под ролью just_employee
    Дано открыть "http://178.154.246.238:58082/"
    Тогда инициализация страницы "DjangoAuthorization"
    Тогда заполнить поле "логин" значением "just_employee"
    И заполнить поле "пароль" значением "hrmhrm123"
    Тогда нажать на "войти"
    Если на странице отсутствует текст "Сообщение об ошибке"
    Тогда инициализация страницы "DjangoEmployeeChange"

    Сценарий: Страница "Изменить Сотрудник", блок "Общая информация", проверка редактирования второстепенных полей
      Если ввести в поле "Отчество" значение "Валентиновна"
      И ввести в поле "Дата приема на работу" значение "20.08.2020"
      И ввести в поле "Телефон" значение "+79883456789"
      И ввести в поле "День рождения" значение "21.06.1988"
      И в выпадющем списке "Гражданство" выбрать случайный элемент
      И нажать на "Сохранить и продолжить редактирование"
      Если поле "Сообщение о успешном редактировании" инфоблок с текстом "The Сотрудник “Бородкина Анастасия” was changed successfully. You may edit it again below." присутствует



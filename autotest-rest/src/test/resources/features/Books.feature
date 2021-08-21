#language:ru
@test

Функционал: Образец GET запроса

  Сценарий: Запрос в bookstore

    Когда создать контекстные переменные
      | book_name | Spring |

    И создать запрос
      | method | url                                              |
      | GET    | https://api.itbook.store/1.0/search/${book_name} |
    Тогда отправить запрос
    И статус код 200

    Когда извлечь данные
      | total_books | $.total |
      | page        | $.page  |

    И сравнить значения
      | ${total_books} | >  | 10 |
      | ${page}        | == | 1  |

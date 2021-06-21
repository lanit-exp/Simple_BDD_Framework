#language:ru

Функционал:

  Сценарий:

    * снегерировать переменные
      | id         | 0                 |
      | username   | EEEEEEEE          |
      | firstName  | EEEEEEEE          |
      | lastName   | EEEEEEEE          |
      | email      | EEEEEEE@EEEDDD.EE |
      | password   | DDDEEEDDDEEE      |
      | phone      | +7DDDDDDDDDD      |
      | userStatus | 1                 |

    * создать запрос
      | method | path  | bodyFromFile    |
      | POST   | /user | createUser.json |
    * добавить header
      | Content-Type | application/json |
    * отправить запрос
    * статус код 200
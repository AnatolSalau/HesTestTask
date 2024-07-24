## Тестовое задание
#### Компания: HES FinTech
#### Выполнил: Салов А. А.


### 1. Запуск приложения

- На GitHub уже загружен скомпилированный JAR HesTestTask.jar по пути "build/libs" ([ссылка](build/libs))
- Для запуска необходимо открыть корень проекта (папку "HesTestTask") в консоли и запустить
  Docker compose файл командой:

      docker-compose -f dev-postgres.docker-compose.yml up -d
- Для остановки запущенного контейнера используйте команду:

      docker-compose -f dev-postgres.docker-compose.yml down

### 2. Postman
Сохраненная коллекция запросов в файле "HesTestTask.postman_collection.json"
HesTestTask - название коллекции после импорта
[Ссылка](HesTestTask.postman_collection.json)

Для аутентификации используйте

Admin

      пароль: admin1
      имя: admin1

      пароль: admin2
      имя: admin2

Owner

      пароль: user3
      имя: user3   

      пароль: user4
      имя: user4  
 
      пароль: user5
      имя: user5  

### 3. Api точки входа

1. Посмотреть все счета как админ

        http://127.0.0.1:8080/api/v1/account
        GET, ROLE Admin, /api/v1/account
2. Заблокировать счет

        http://127.0.0.1:8080/api/v1/account/1/block
        PUT, ROLE Admin  /api/v1/account/{account_id}/block
3. Разблокировать счет

        http://127.0.0.1:8080/api/v1/account/1/block
        PUT, ROLE Admin  /api/v1/account/{account_id}/unblock
4. Посмотреть список счетов данного пользователя

        http://127.0.0.1:8080/api/v1/user/3/account
        GET, ROLE User, /api/v1/user/{user_id}/account
5. Пополнить счет

        http://127.0.0.1:8080/api/v1/account/deposit
        PUT, ROLE User, /api/v1/account/deposit

        {
            "userId" : 3,
            "accountId" : 1,
            "amount": 300.00
        }
6. Снять счет

        http://127.0.0.1:8080/api/v1/account/withdraw
        PUT, ROLE User, /api/v1/account/withdraw

        {
            "userId" : 3,
            "accountId" : 1,
            "amount": 300.00
        }
Actuator

        http://127.0.0.1:8080/actuator/health
        http://127.0.0.1:8080/actuator/info
Api 3.0

        http://127.0.0.1:8080/api-docs
Swagger

        http://127.0.0.1:8080/swagger-ui/index.html

### 3. БД
   Миграция через реализована liquidbase

   Название схемы hes

   Три таблицы users, accounts, transactions 

   Тип валюты, вид транзакции, роль вынесены в отдельные типы: 
   
   currencytype, transactiontype, roletype

### 3. Security
   Аутентификация реализована через Basic http authentication

   Роль у каждого может быть только одна
   
### 6. Комментарии
   Добавил кое-какие интеграционные тесты, так как все равно во время разработки их создал

   Oбработка ошибок через AdviceController

   Оставлю ссылку на предыдущее тестовое задание, там OAUT2.0 

   аутентификация через authorization code flow и client credential flow

   ([Предыдущее тестовое](https://github.com/AnatolSalau/AGSRTestTaskTest2))

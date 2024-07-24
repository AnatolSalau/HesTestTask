Endpoints

    http://127.0.0.1:8080/api/v1/account
    GET, ROLE Admin, /api/v1/account - посмотреть все счета как админ
    
    POST, ROLE Admin  /api/v1/account/{account_id}/block - заблокировать счет счет

    POST, ROLE Admin  /api/v1/account/{account_id}/unblock/ - разблокировать счет счет

    GET, ROLE User, /api/v1/user/{user_id}/accounts - посмотреть список счетов данного пользователя

    POST, ROLE User, /api/v1/account/{account_id}/deposit - пополнить счет

    POST, ROLE User, /api/v1/account/{account_id}/withdraw - снять счет

Actuator

    http://127.0.0.1:8080/actuator/health
    http://127.0.0.1:8080/actuator/info
Api 3.0

    http://127.0.0.1:8080/api-docs
Swagger

    http://127.0.0.1:8080/swagger-ui/index.html
    
# Дипломный проект по профессии «Тестировщик»

Ссылка на задание:

`https://github.com/netology-code/qa-diploma`

Задача дипломного проекта автоматизировать позитивные и негативные сценарии покупки тура.

[План автоматизации тестирования веб-сервиса](https://github.com/IQA69/QA_Graduate_work/blob/main/Plan.md)

## Запуск автотестов

- Склонировать репозиторий с Github по ссылке:

`https://github.com/IQA69/QA_Graduate_work`

- Открыть проект в IntelliJ IDEA

Для запуска нужно заранее установить, настроить и запустить Docker Desktop

В терминале IDEA ввести указанные команды.

### Запуск контейнеров

`docker-compose up --build`

### Запуск веб-сервиса

Для MySQL:

`java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar`

Для PostgreSQL:

`java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar`

Приложение должно быть доступно по адресу:

`http://localhost:8080/`

## Запуск тестов

Для MySQL:

`./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"`

Для PostgreSQL:

`./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"`

Генерация отчёта Allure с автоматическим открытием в браузере:

`./gradlew allureServe`

## Остановка сервиса и контейнеров

`CTRL+C` в окне терминала, где был запущен веб-сервис

Остановка и удаление контейнеров:

`docker-compose down`
## 1. Перечень автоматизируемых сценариев.

Настройка учетных данных и url в файле application.properties.

Установка и запуск требуемых СУБД.

Установка и запуск эмулятора банковского сервиса.

Создание в IntelliJ IDEA тестов с положительным и отрицательным результатами.

Создание в IntelliJ IDEA тестов с положительным и отрицательным результатами по покупке продукта дебетовой картой.

Создание в IntelliJ IDEA тестов с положительным и отрицательным результатами по покупке продукта при помощи кредита оформленного по данным карты.

#### Предусловие для всех позитивных и негативных сценариев с оплатой по дебетовой карте:

Открыть в браузере страницу сайта http://localhost:8080/

Нажать на кнопку Купить

Появляется вкладка Оплата по карте

### Позитивные сценарии:

Заполнить поле Номер карты цифрами 4444 4444 4444 4441

Заполнить поля Месяц, Год, Владелец, CVC/CVV валидными данными

Нажать кнопку Продолжить

Ожидаемый результат: Появляется сообщение Оплата прошла успешно.

### Негативные сценарии:

Заполнить пле Номер карты цифрами 4444 4444 4444 4442

Заполнить поля Месяц, Год, Владелец, CVC/CVV невалидными данными

Нажать кнопку Продолжить

Ожидаемый результат: Появляется сообщение об ошибке. Оплата не произведена.

#### Предусловие для всех позитивных и негативных сценариев с оплатой по кредитной карте:

Открыть в браузере страницу сайта http://localhost:8080/

Нажать на кнопку Купить в кредит

Появляется вкладка Кредит по данным банковской карты

### Позитивные сценарии:

Заполнить поле Номер карты цифрами 4444 4444 4444 4441

Заполнить поля Месяц, Год, Владелец, CVC/CVV валидными данными

Нажать кнопку Продолжить

Ожидаемый результат: Появляется сообщение Кредит оформлен успешно.

### Негативные сценарии:

Заполнить пле Номер карты цифрами 4444 4444 4444 4442

Заполнить поля Месяц, Год, Владелец, CVC/CVV невалидными данными

Нажать кнопку Продолжить

Ожидаемый результат: Появляется сообщение об ошибке. Невозможно оформить кредит.

## 2. Перечень используемых инструментов с обоснованием выбора.

IntelliJ IDEA - среда разработки ПО для написания, исполнения, отладки и оптимизации кода для Java.

Java - язык программирования для написания автотестов.

Selenide - фреймворк для автоматизированного тестирования веб-приложений.

Gradle - система автоматической сборки.

Docker - контейнеризатор приложений.

Docker Compose - инструмент для определения и запуска многоконтейнерных приложений.

Allure - фреймворк для геренации отчёта.

GitHub - место для хранения кода.

## 3. Перечень и описание возможных рисков при автоматизации.

Риск некорректно работающего функционала.

Изменение целостности страницы.

Изменение внешнего вида страницы.

Риск возникновения проблем при вводе данных.

Создание новых ошибок при проведение автотестов.

Сбой настроек окружения.

Сбой работы инфраструктуры.

## 4. Интервальная оценка с учётом рисков в часах.

Планирование автоматизации тестирования - 48 часов.

Запуск IntelliJ IDEA, настройка окружения, развёртывание БД - 14 часов.

Тестирование (ручное + написание и проведение автотестов), отладка - 120 часов.

Написание отчётов - 72 часа.

## 5. План сдачи работ (когда будут готовы автотесты, результаты их прогона).

Планирование автоматизации тестирования - до 03.03.2024.

Запуск IntelliJ IDEA, настройка окружения, развёртывание БД - до 10.03.2024.

Тестирование (ручное + написание и проведение автотестов), отладка - до 10.04.2024.

Написание отчётов - до 26.04.2024.
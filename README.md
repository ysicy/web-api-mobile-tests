<div align="center"> <h1> Демонстрационный автоматизированный проект на своей инфраструктуре  </a> </h1> </div>

**Технологии и инструменты:**
<div align="center">
<a href="https://www.jetbrains.com/idea/"><img alt="InteliJ IDEA" height="50" src="images/jetbrains-original.svg" width="50"/></a>
<a href="https://www.jetbrains.com/idea/"><img alt="Aerokube" height="50" src="images/aerokube_logo.svg" width="50"/></a>
<a href="https://www.jetbrains.com/idea/"><img alt="Allure" height="50" src="images/allure.png" width="50"/></a>
<a href="https://www.jetbrains.com/idea/"><img alt="Gitlab" height="50" src="images/gitlab-original.svg" width="50"/></a>
<a href="https://www.jetbrains.com/idea/"><img alt="Gradle" height="50" src="images/gradle-original.svg" width="50"/></a>
<a href="https://www.jetbrains.com/idea/"><img alt="Java 17" height="50" src="images/java-original.svg" width="50"/></a>
<a href="https://www.jetbrains.com/idea/"><img alt="Jenkins" height="50" src="images/jenkins-line.svg" width="50"/></a>
<a href="https://www.jetbrains.com/idea/"><img alt="Junit" height="50" src="images/junit-original.svg" width="50"/></a>
<a href="https://www.jetbrains.com/idea/"><img alt="Rest-Assured" height="50" src="images/rest-assure.png" width="50"/></a>
<a href="https://www.jetbrains.com/idea/"><img alt="Telegram" height="50" src="images/Telegram_logo.svg" width="50"/></a>

</div>

**Особенности проекта**:
- `Page Object` шаблон проектирования
- Возможность запуска тестов: локально, удалённо, по тегам
- Использование `Lombok` для моделей в API тестах
- Так же, в API тестах для сериализации и десериализации объектов используется библиотека `Jaсkson`
- По итогу прохождения автотестов генерируется `Allure отчет`. Содержание отчета:
    - Шаги теста
    - Скриншот страницы на последнем шаге
    - Исходный код страницы в браузере
    - Логи консоли браузера
    - Видео выполнения автотеста
- По прохождению тестов формируется и отправляется уведомление о результатах прохождения в `Telegram-канал` в виде Allure отчета


# На сайте [Большая Российская Энциклопедия](https://bigenc.ru/) выполняются проверки в части WEB:


- [x] Проверка текста на странице авторизации
- [x] Проверка перехода на статическую страницу
- [x] Проверка отправки значения в строку поиска (Параметризованный тест)
- [x] Проверка ввода и поиска значения на странице Каталога (Параметризованный тест)
- [x] Проверка невозможности отправки заявки без заполнения формы Стать автором"
# На сайте [FAKESTOREAPI](https://fakestoreapi.com/) выполняются проверки в части API:


- [x] Получение списка всех пользователей
- [x] Получение определенного пользователя по id
- [x] Получение списка пользователей с заданным limit
- [x] Получение списка пользователей отсортированного по DESC
- [x] Добавление нового пользователя
- [x] Удаление пользователя
- [x] Успешная авторизация
- [x] Неуспешная Авторизация

# Запуск тестов
### Допустимые комбинации

```mermaid 

flowchart LR
    A(gradle) --> B(clean)
    B --> C{Выбрать тег}
    C --> D[ui_test]
    C --> E[running_test]
   
```

## Для **удаленного** запуска на **собственной** машине установлен **Docker**, в котором:
- Поднят `docker-container` с `Selenoid` на порте: **4444**
- Поднят `docker-container` с `Selenoid-UI`на порте: **8080**
- Поднят `docker-container` с `Jenkins` на порте: **8081**
- В `Jenkins` заведены 2 тачки: `linux-agent` и `windows-agent`  

### Создан проект с 2 item's:

1. `linux-agent`
-  на `linux-agent` выполняется проверка тестов по тегу `api`  
- Сборка собрана на основе `groovy-script`
2. `windows-agent`
- на `windows-agent` выполняются проверки тестов по тегу `ui`  
- Сборка собрана на основе `Jankinsfile`

Для примера: выбран item для проверок UI части на `windows-agent`
<img src="images/selenoidwork.jpg">
Пример выполнения тестов на `Selenoid`
<p align="center">
  <img src="images/Selenoidgif.gif">
</p>

> К каждому Web UI-тесту в отчете прилагается видео.   
>  Так же, Allure отчет доступен локально:
>
> 
><img src="images/jenkinsproject.png">


## Уведомления в Telegram с использованием бота
>
> После завершения item выполняется скрипт отправки уведомления `Allure отчета` в `Telegram`
>
><img src="images/telega.jpg">




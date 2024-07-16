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
- По итогу прохождения автотестов генерируется `Allure отчет`. Содержание отчета:
    - Шаги теста
    - Скриншот страницы на последнем шаге
    - Исходный код страницы в браузере
    - Логи консоли браузера
    - Видео выполнения автотеста
    
Для **удаленного** запуска на собственной машине установлен **Docker**, в котором:
- Поднят `docker-container` с `Selenoid` на порте: **4444**
- Поднят `docker-container` с `Selenoid-UI`на порте: **8080**
- Поднят `docker-container` с `Jenkins` на порте: **8081**
- В `Jenkins` заведены 2 тачки: `linux-agent` и `windows-agent`
- Созданы 2 item's:
- на `linux-agent` выполняется проверка тестов по тегу `api`
- Сборка собрана на основе `groovy-script`
- на `windows-agent` выполняются проверки тестов по тегу `ui`
- Сборка собрана на основе `Jankinsfile`
- По прохождению тестов формируется и отправляется уведомление о результатах прохождения в `Telegram-канал`
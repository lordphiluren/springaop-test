# Логировние с использованием Spring AOP
Тестовый проект показывающий возможности Spring AOP для логирования при помощи Slf4j.

## Описание проекта
Создано простое Spring приложение, содержащее сервисы для управления пользователями и их заказами. Используется Spring AOP для логирования вызовов этих сервисов.
- Реализованы сущности User, Order
- Реализованы сервисы UserService, OrderService, содержащие базовые CRUD операции над сущностями
- Создан аспект LoggingAspect, который содержит логику логирования операция в сервисах
- Написаны базовые тесты для проверки правильности логирования
### Логика логирования
Логи выводятся в консоль
Реализованы следующие виды логов:
- Логи для всех методов сервисов, которые выводят название вызванного метода метод и его входные параметры
```
INFO Before execution(User ru.sushchenko.springaopapp.service.UserService.addUser(User)), args=[User(id=null, name=Alice, email=alice@example.com)]
```
- Логи для всех методов сервисов, которые выводят название вызванного метода и возвращенное значение
```
INFO After execution(User ru.sushchenko.springaopapp.service.UserService.addUser(User)), returns=[User(id=1, name=Alice, email=alice@example.com)]
```
- Логи, которые выводят информацию об обновлении сущности, реализована аннотация UpdateAnnotation, которой помечаются методы для обновления
```
INFO User with id: 2, updated: User(id=2, name=UPDATED NAME, email=bob@example.com)
```
- Логи, которые выводят вызванное в методе сервиса исключение
```
ERROR execution(User ru.sushchenko.springaopapp.service.UserService.getUser(Long)) threw exception=[ru.sushchenko.springaopapp.utils.EntityNotFoundException: User id: 23132131 not found]
```
- Логи, выводящие информацию об удалении сущности
```
INFO User with id: 1 deleted
```

## Использованные технологии
- Java
- Gradle
- Spring Boot, Spring Data JPA, Spring AOP
- PostgreSQL
- Docker - контейнеры, образы, volumes, написание Dockerfile, Docker Compose

## Инструкция по запуску проекта на локальном компьютере
- Склонировать репозиторий 
```
git clone https://github.com/lordphiluren/springaop-test
```
- Перейти в папку с проектом
- Запустить тесты
```
./gradlew test
```

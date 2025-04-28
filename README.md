# TestTask
Основные компоненты
Веб-слои и безопасность

    Spring Web для создания REST API
    Spring Security для обеспечения безопасности
    JWT (JSON Web Tokens) для аутентификации и авторизации
    OpenAPI UI (версия 2.8.5) для документирования API

Слой данных

    PostgreSQL как основная база данных
    Redis для кэширования данных
    Spring Data JPA/Hibernate для работы с БД
    Liquibase для управления миграциями схемы базы данных

Тестирование и CI/CD

    TestContainers для интеграционного тестирования
    Поддержка контейнеров PostgreSQL и Redis для тестов
    JUnit Jupiter для юнит-тестов
    Spring Security Test для тестирования безопасности

Инструменты разработки

    Lombok для сокращения boilerplate-кода
    MapStruct (версия 1.5.5.Final) для маппинга объектов
    Автоматическая генерация кода через аннотации

Запуск проекта:
1)перейти в директорию проекта
2)выполнить команду docker compose up
3)mvn spring-boot:run
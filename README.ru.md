# XmlConverter

Простое приложение для преобразования файлов с расширением xml в csv.

## Описание проекта

Проект предназначен для преобразования файлов, содержащих информацию о рейсах аэропорта в формате '.xml',
в файлы формата '.csv'.

## Технологии

- Проект написан на Java, версии 17 (Oracle JDK)
- IDE: IntelliJ IDEA
- Сборка проекта: Gradle

## Зависимости

- [Apache Commons CSV](https://mvnrepository.com/artifact/org.apache.commons/commons-csv) версии 1.10.0
- [IO Freefair Lombok](https://plugins.gradle.org/plugin/io.freefair.lombok) version 6.6.3

## Установка

Чтобы установить проект, склонируйте репозиторий с GitHub:

```bash
git clone https://github.com/xissah/XmlConverter.git
```

## Тестирование

Проект содержит модуль с тестовыми методами и ресурсами.  
Который использует следующие зависимости:

- [Junit Jupiter API](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api) версии 5.9.2
- [JUnit Jupiter Engine](https://mvnrepository.com/search?q=junit) версии 5.9.2
- [Mockito Core](https://mvnrepository.com/artifact/org.mockito/mockito-core) версии 5.6.0
- [Mockito JUnit Jupiter](https://mvnrepository.com/artifact/org.mockito/mockito-junit-jupiter) версии 5.6.0

## Особенности

Вывод в консоль данных и запись файлов осуществляется в кодировке UTF-8.
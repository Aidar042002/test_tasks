# Микросервис для хранилища файлов и их атрибутов

## Описание задания

Реализовать микросервис для хранилища файлов и их атрибутов.

### Функциональные возможности

- **Создание файла**:
  - На вход методу отправляется JSON, включающий в себя файл (в формате base64) и его атрибуты (название - `title`, дата и время отправки - `creation_date`, краткое описание документа - `description`).
  - На выходе метод возвращает `id` созданного файла.

- **Получение файла**:
  - На вход методу отправляется `id` файла.
  - На выходе метод возвращает JSON, включающий в себя файл (в формате base64) и его атрибуты (название - `title`, дата и время отправки - `creation_date`, краткое описание документа - `description`).

- **Получение списка всех файлов (и их атрибутов)**.

## Описание решения

Код находится в пакете `convert_file_base64`, класс для запуска `ConvertFileBase64Application.java`.

### Структура проекта

- **Файл с настройками БД**: `src/main/resources/application.properties`
- **Класс для юнит-тестов**: `convert_file_base64/src/test/java/com/example/convert_file_base64/controllers/FileControllerTest.java`
- **Класс сущность**: `FileEntity`
- **Интерфейс репозитория**: `FileRepository`
- **Класс сервис**: `FileService`
- **Класс модель**: `FileDTO`
- **Класс контроллер с тремя методами**: `FileController`

### Методы API

1. **Метод POST createFile для создания файла**
   - Путь: `http://localhost:8080/api/files`
   - Пример входных данных в теле метода в формате JSON:
     ```json
     {
         "file": "U29tZSBzYW1wbGUgZmlsZSBjb250ZW50IGluIGJhc2U2NA==",  
         "title": "Sample File two",
         "creationDate": "2024-07-27T14:30:00", 
         "description": "This is a sample file for testing"
     }
     ```

2. **Метод GET getFile для получения файла по id**
   - Путь: `http://localhost:8080/api/files/{id}`

3. **Метод GET getAllFiles для получения всех файлов**
   - Путь: `http://localhost:8080/api/files`

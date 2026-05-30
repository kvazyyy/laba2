# Лабораторная работа №2 — Стабилизированный анализатор миссий магов

## Что реализовано

- поддержка форматов: JSON, XML, TXT, YAML/YML, событийный протокол LOG;
- единая доменная модель миссии;
- паттерны: Factory, Builder, Strategy, Template Method, Registry;
- отдельные пакеты `Analysis`, `Builders`, `Entities`, `Filtering`, `GUI`, `Logging`, `Parsers`, `Reports`, `Validation`;
- краткий и детализированный отчёт;
- пакетная обработка папки с файлами миссий.


## Запуск одного файла

```bash
java -jar target/mission-analyzer-lab2-2.0.jar src/main/resources/examples/mission_lab2.json summary
java -jar target/mission-analyzer-lab2-2.0.jar src/main/resources/examples/mission_lab2.xml detailed
```

## Пакетная обработка

```bash
java -jar target/mission-analyzer-lab2-2.0.jar --batch src/main/resources/examples detailed
```
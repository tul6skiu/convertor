# Тестовое задание на вакансию Java разработчика

**Используемые технологии (строгое соблюдение):**

- Java 11 или Java 13
- Maven
- Spring boot 2.2.4
- PostgresSQL 12
- Html 5\CSS (Можно React.js, Angular.js, Vue.js и.т.д)

**Задача:**

Сделать конвертер валют


# Запуск (cтрогое соблюдение)
 1. Установите PostgresSQL локально себе на компьютер.
 2. Откройте терминал и выполните следующие команды:
 
  ```code
  sudo -i -u postgres
  psql
  ```
 3. В этом же терминале создайте пользователя:
  ```sql
  CREATE USER foobar PASSWORD 'foobar';
  CREATE DATABASE foobar OWNER foobar;
  ```
 4. Выполните выход:
 ```code
 postgres=# \q
 postgres@student:~$ exit
 ```
 5. Откройте терминал и выполните следующие команды:
 ```
 psql -h localhost -U foobar
 psql 
 CREATE SCHEMA converter;
 ```

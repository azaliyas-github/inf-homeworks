# Взаимодействие с БД в приложении

* JDBC - концепция, которая подразумевает наличие драйверов для БД.

Существует пакет `java.sql` в котором описаны базовые интерфейсы
для работы с БД в Java - Connection, Statement, ResultSet и т.д.

Реализация всех интерфейсов - в конкретном драйвере.

* ORM - классы = таблицы, объекты = строки

* JPA - стандарт ORM в Java - javax.persistence.*, данный пакет
содержит интерфейсы и аннотации для реализации в ORM в Java.

- @Entity
- @Id
- @Table
- @ManyToMany
- @OneToMany
- @Transient

* Hibernate - ORM-фреймворк, реализация JPA. Генерирует SQL

* Spring Data JPA - фреймворк JPA + Hibernate + JpaRepository
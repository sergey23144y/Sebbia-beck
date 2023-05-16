package com.example.db


import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.Table

class dbHelper {

    fun creatyTable(name_user : String) {
        // Создаем соединение с базой данных PostgreSQL
        Database.connect(
            url = "jdbc:postgresql://localhost:5432/sebbia",
            driver = "org.postgresql.Driver",
            user = "postgres",
            password = "qwerty"
        )

        // Создаем таблицу users
//        transaction {
//            addLogger(StdOutSqlLogger) // Логируем SQL-запросы


//            // Вставляем одну запись в таблицу
//            Users1.insert {
//                it[name] = name_user
//            }
    }


    // Описание таблицы users
//    object Users1 : Table() {
//        val id: Column<Int> = integer("id").autoIncrement()
//        val name: Column<String> = varchar("name", 50)
//    }
    fun addUser() {
        Database.connect(
            url = "jdbc:postgresql://localhost:5432/sebbia",
            driver = "org.postgresql.Driver",
            user = "postgres",
            password = "qwerty"
        )

        // Создаем таблицу users
        transaction {
            addLogger(StdOutSqlLogger) // Логируем SQL-запросы

//            // Вставляем одну запись в таблицу
//           Users.insert {
//                it[name] = "1"
//                it[login] = "1"
//                it[surname] = "1"
//                it[middle_name] = "1"
//                it[token_short] = "1"
//                it[token_long] = "1"
//                it[password] = "1"
//                it[Type_of_activity] = 1
//            }
        }
    }
    object Users : Table("users") {
        val id: Column<Int> = Users.integer("id").autoIncrement()
        val login: Column<String> =  Users.varchar("login", 255)
        val password: Column<String> =  Users.varchar("password", 64)
        val surname: Column<String> =  Users.varchar("surname", 64)
        val name: Column<String> =  Users.varchar("name", 64)
        val middle_name: Column<String> =  Users.varchar("middle_name", 64)
        val Type_of_activity: Column<Int> =  Users.integer("type_of_activity")
        val token_short : Column<String> =  Users.varchar("token_short", 64)
        val token_long : Column<String> =  Users.varchar("token_long", 64)
    }

}



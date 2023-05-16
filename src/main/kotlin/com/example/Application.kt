package com.example

import com.example.db.dataDb.password
import com.example.db.dataDb.url
import com.example.db.dataDb.user
import com.example.db.dbHelper
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import org.flywaydb.core.Flyway

fun main() {

// настраиваем Flyway
    val flyway = Flyway.configure()
        .dataSource(url, user, password)
        .baselineOnMigrate(true)
        .locations("db/migration") // указываем папку с миграциями
        .load()
// запускаем миграции
    flyway.migrate()

    var dbHelper = dbHelper()
    dbHelper.addUser()

    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    CreaytUser()
    SumNambers()
    configureRouting()
    login()
}

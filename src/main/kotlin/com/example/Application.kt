package com.example

import CommentContriller
import com.example.db.Description.DescriptionContriller
import com.example.db.Role.RoleContriller
import com.example.db.Role.RoleModel.getRole
import com.example.db.Task.TaskContriller
import com.example.db.UserRoleProject.UserRoleProjectController
import com.example.db.dataDb.password
import com.example.db.dataDb.url
import com.example.db.dataDb.user
import com.example.features.login.configureLoginRouting
import com.example.features.register.configureRegisterRouting
import com.example.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database


fun main() {
    // настраиваем Flyway
    val flyway = Flyway.configure()
        .dataSource("jdbc:postgresql://localhost:5432/sebbia", "postgres", "111")
        .baselineOnMigrate(true)
        .locations("db/migration") // указываем папку с миграциями
        .load()

// запускаем миграции
    flyway.migrate()


    Database.connect(
        url = "jdbc:postgresql://localhost:5432/sebbia",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "111"
    )

   embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    RoleContriller()
    TaskContriller()
    UserRoleProjectController()
    configureLoginRouting()
    configureSerialization()
    CreaytUser()
    SumNambers()
    configureRouting()
    filetext()
    photo()
    configureRegisterRouting()
    DescriptionContriller()
    CommentContriller()
}

package com.example



import com.example.db.UserRoleProject.UserRoleProjectDTO
import com.example.db.UserRoleProject.UserRoleProjectModel
import com.example.db.dataDb.password
import com.example.db.dataDb.url
import com.example.db.dataDb.user
import com.example.db.dbHelper
import io.ktor.server.application.*
import com.example.plugins.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database


fun main() {
    Database.connect(
        url = "jdbc:postgresql://localhost:5432/Sebbia",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "123321"
    )

   embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)

    val userRoleProjectDTO = UserRoleProjectDTO(null,null,null,1 )

    UserRoleProjectModel.insert(userRoleProjectDTO)
    println(UserRoleProjectModel.fetchTask(5).id)

    var dbHelper = dbHelper()
    dbHelper.addUser()

// настраиваем Flyway
    val flyway = Flyway.configure()
        .dataSource(url, user, password)
        .baselineOnMigrate(true)
        .locations("db/migration") // указываем папку с миграциями
        .load()

// запускаем миграции
    flyway.migrate()
}

fun Application.module() {
    CreaytUser()
    SumNambers()
    configureRouting()
    login()
    filetext()
    photo()
}

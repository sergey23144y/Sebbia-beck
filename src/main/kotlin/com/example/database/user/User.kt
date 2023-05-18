package com.example.database.user

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object User: Table("ussers") {
    val id = User.integer("id")
    val login =  User.varchar("login", 255)
    val password =  User.varchar("password", 64)
    val token_short =  User.varchar("token_short", 64)
    val token_long =  User.varchar("token_long", 64)

   fun insert(usersDTO: UsersDTO){
       transaction {
           User.insert {
               it[login] = login
               it[password] = password
               it[token_short] = token_short
               it[token_long] = token_long
           }
       }
   }

    // Извлекаем user
    fun fetchUser(login: String): UsersDTO? {
        return try {
            val user = User.select { User.login.eq(login) }.single()
             UsersDTO(
                login = user[User.login],
                password = user[password],
                token_short = user[token_short],
                token_long = user[token_long]
            )
        } catch (e: Exception) {
            null
        }
    }
}
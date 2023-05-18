package com.example.database.user

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object usser: Table("usser") {
    val id = usser.integer("id")
    val login =  usser.varchar("login", 50)
    val password =  usser.varchar("password", 50)
    val token_short =  usser.varchar("token_short", 50)
    val token_long =  usser.varchar("token_long", 50)
    val personId = usser.integer("personid")

   fun  insert (usersDTO: UsersDTO){
       transaction {
           usser.insert {
               it[login] = usersDTO.login
               it[password] = usersDTO.password
               it[token_short] = usersDTO.token_short
               it[token_long] = usersDTO.token_long
           }
       }
   }

    // Извлекаем user
    fun fetchUser(login: String): UsersDTO? {
        return try {
            val user = usser.select { usser.login.eq(login) }.single()
             UsersDTO(
                login = user[usser.login],
                password = user[password],
                token_short = user[token_short],
                token_long = user[token_long],
                 personId = user[personId]
            )
        } catch (e: Exception) {
            null
        }
    }
}
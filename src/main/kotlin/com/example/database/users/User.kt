package com.example.database.users

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object User: Table("ussers") {
    val id = User.integer("id")
    val login =  User.varchar("login", 255)
    val password =  User.varchar("password", 64)
    val surname =  User.varchar("surname", 64)
    val name =  User.varchar("name", 64)
    val middle_name =  User.varchar("middle_name", 64)
    val type_of_activity =  User.integer("type_of_activity")
    val token_short =  User.varchar("token_short", 64)
    val token_long =  User.varchar("token_long", 64)

   fun insert(usersDTO: UsersDTO){
       transaction {
           User.insert {
               it[login] = usersDTO.login
               it[password] = usersDTO.password
               it[surname] = usersDTO.surname
               it[name] = usersDTO.name
               it[middle_name] = usersDTO.middle_name
               it[type_of_activity] = usersDTO.type_of_activity
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
                surname = user[surname],
                name = user[name],
                middle_name = user[middle_name],
                type_of_activity = user[type_of_activity],
                token_short = user[token_short],
                token_long = user[token_long]
            )
        } catch (e: Exception) {
            null
        }
    }
}
package com.example.features.login

import com.example.database.user.User
import com.example.database.user.UsersDTO
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.util.*

class LoginController(private val call: ApplicationCall) {
    suspend fun performLogin(){
        val receive = call.receive<LoginReceiveRemote>()
        val userDTO = User.fetchUser(receive.login)

        if(userDTO == null){
            call.respond(HttpStatusCode.BadRequest, "User not found")
        } else {
            if(userDTO.password== receive.password){
                val token = UUID.randomUUID().toString()
                User.insert(
                    UsersDTO(
                        login = receive.login,
                        password = receive.login,
                        token_short = "",
                        token_long = token
                    )
                )
                call.respond(LoginResponseRemote(token = token))
            } else{
                call.respond(HttpStatusCode.BadRequest, "Invalid password")
            }
        }
    }
}
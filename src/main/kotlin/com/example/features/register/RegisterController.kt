package com.example.features.register


import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import java.util.*
import  com.example.database.users.User
import com.example.database.users.UsersDTO
import com.example.utils.isValidPassword
import io.ktor.server.request.*


class RegisterController(val call: ApplicationCall) {

   suspend fun registerNewUser(){
       val registerReciveRemote = call.receive<RegisterReciveRemote>()
       if(!registerReciveRemote.password.isValidPassword()){
           call.respond(HttpStatusCode.BadRequest, "Email is not")
       }

       var token : String = ""
       val userDTO = User.fetchUser(registerReciveRemote.login)
       if(userDTO != null){
           call.respond(HttpStatusCode.Conflict, "User already exists")
       }
       else{
           token = UUID.randomUUID().toString()
           User.insert(
               UsersDTO(
                    login = registerReciveRemote.login,
                    password = registerReciveRemote.login,
                    surname = "rewe",
                    name = "rwe",
                    middle_name = "erw",
                    type_of_activity = 0,
                    token_short = "",
                    token_long = ""
               )
           )
       }
        call.respond(RegisterResponseRemote(token = token))
    }
}
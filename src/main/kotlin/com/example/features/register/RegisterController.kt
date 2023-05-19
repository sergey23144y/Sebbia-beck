package com.example.features.register


import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import java.util.*
import  com.example.database.user.usser
import com.example.database.user.UsersDTO
import com.example.utils.isValidPassword
import io.ktor.server.request.*


class RegisterController(val call: ApplicationCall) {

   suspend fun registerNewUser(){
       val registerReciveRemote = call.receive<RegisterReciveRemote>()
       if(!registerReciveRemote.password.isValidPassword()){
           call.respond(HttpStatusCode.BadRequest, "Email is not")
       }

       var token : String = ""
       val userDTO = usser.fetchUser(registerReciveRemote.login)
       if(userDTO != null){
           call.respond(HttpStatusCode.Conflict, "User already exists")
       }
       else{
           token = UUID.randomUUID().toString()
           usser.insert(
               UsersDTO(
                    login = registerReciveRemote.login,
                    password = registerReciveRemote.password,
                    token_short = "",
                    token_long = ""
               )
           )
       }
        call.respond(RegisterResponseRemote(token = token))
    }
}
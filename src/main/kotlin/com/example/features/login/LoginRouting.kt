package com.example.features.login

import com.example.cache.InMemoryCache
import com.example.cache.TokenCache
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import java.util.*

fun Application.configureLoginRouting() {
    routing {
        post("/login") {

            val receive = call.receive<LoginReceiveRemote>()
            if (InMemoryCache.userList.map {it.login }.contains(receive.login)){
                // Генерация JWT-токена
                val token = UUID.randomUUID().toString()
                InMemoryCache.token.add(TokenCache(login = receive.login, token = token))
                call.respond(LoginResponseRemote(token = token))

            }
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}
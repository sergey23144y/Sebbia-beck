package com.example.plugins



import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.contentnegotiation.*
import org.jetbrains.exposed.dao.exceptions.EntityNotFoundException


fun Application.configureSerialization() {
  install(ContentNegotiation){
    json()
  }


}









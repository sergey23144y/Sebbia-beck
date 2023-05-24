package com.example.db.UserRoleProject

import com.example.db.UserRoleProject.UserRoleProjectModel.deleteURP
import com.example.db.UserRoleProject.UserRoleProjectModel.getURP
import com.example.db.UserRoleProject.UserRoleProjectModel.getURPAll
import com.example.db.UserRoleProject.UserRoleProjectModel.insert
import com.example.db.UserRoleProject.UserRoleProjectModel.updateURP
import com.google.gson.Gson
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.UserRoleProjectController() {
    routing {
        route("/user_role_project") {
            get {
                val URPDTO = getURPAll()
                val gson = Gson()
                call.respond(gson.toJson(URPDTO))
            }

            get("/{id}") {
                val URPId = call.parameters["id"]?.toIntOrNull()
                if (URPId != null) {
                    val URPDTO = getURP(URPId)
                    call.respond(URPDTO!!)
                } else {
                    call.respond(HttpStatusCode.BadRequest, "Invalid ID format.")
                }
            }

            post {
                val urp = call.receive<String>()
                val gson = Gson()

                val name = gson.fromJson(urp, UserRoleProjectDTO::class.java)
                insert(name)
                call.respond(HttpStatusCode.Created)
            }

            put("/{id}") {
                val urpId = call.parameters["id"]?.toIntOrNull()
                if (urpId != null) {
                    val urp = call.receive<String>()
                    val gson = Gson()

                    val URPDTO = gson.fromJson(urp, UserRoleProjectDTO::class.java)
                    call.respond(updateURP(urpId, URPDTO))
                } else {
                    call.respond(HttpStatusCode.BadRequest, "Invalid ID format.")
                }
            }

            delete("/{id}") {
                val URPId = call.parameters["id"]?.toIntOrNull()
                if (URPId != null) {
                    call.respond(deleteURP(URPId), "Delete")
                } else {
                    call.respond(HttpStatusCode.BadRequest, "Invalid ID format.")
                }
            }
        }
    }
}
package com.example.db.Role

import com.example.db.Description.DescriptionDTO
import com.example.db.Description.DescriptionModel
import com.example.db.person.PersonDTO
import com.example.db.person.PersonModel
import com.example.db.person.PersonModel.autoIncrement
import com.example.db.person.PersonModel.primaryKey
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object RoleModel: Table("role") {
    private val id = RoleModel.integer("id").autoIncrement().primaryKey()
    private val name = RoleModel.varchar("name", 64)


    fun getRole(id:Int): RoleDTO {

        lateinit var roleDTO: RoleDTO

        transaction {
            val roleModel = RoleModel.select { RoleModel.id.eq(id) }.single()
            roleDTO = RoleDTO(
                id = roleModel[RoleModel.id],
                name = roleModel[RoleModel.name]

                )
        }
        return roleDTO
    }

    fun getRoleAll(): List<RoleDTO> {

        return transaction {
            RoleModel.selectAll().map {
                RoleDTO(
                    id = it[RoleModel.id],
                    name = it[RoleModel.name],


                    )
            }
        }


    }
}
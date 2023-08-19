package me.ilya.application.services

import me.ilya.application.entitys.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository :JpaRepository<User,Long>{
    fun findByUsername(username : String?): User?
    fun findByEmail(username : String?): User?

}
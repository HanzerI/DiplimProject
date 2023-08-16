package com.example.demo.services

import com.example.demo.entitys.User
import com.example.demo.sotial.SocialNetwork
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository :JpaRepository<User,Long>{
    fun findByUsername(username : String?): User?
    fun findByEmail(username : String?): User?

}
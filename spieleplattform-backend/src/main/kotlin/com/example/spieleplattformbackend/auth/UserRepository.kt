package com.example.spieleplattformbackend.auth

import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<UserEntity, String> {
    fun getUserByEmail(email: String): UserEntity?

    fun existsByEmail(email: String): Boolean
}
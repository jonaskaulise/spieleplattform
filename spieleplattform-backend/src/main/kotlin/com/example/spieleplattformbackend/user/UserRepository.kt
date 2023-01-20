package com.example.spieleplattformbackend.user

import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, String> {
    fun getUserByUsername(username: String): User?
}
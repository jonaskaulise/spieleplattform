package com.example.spieleplattformbackend.user

data class User(
    val username: String,
    val firstname: String,
    val lastname: String,
    val email: String,
    val roles: List<*>,
    val id: String
) {

    fun isAuthor(): Boolean {
        return roles.any { role -> role == "author" }
    }
}
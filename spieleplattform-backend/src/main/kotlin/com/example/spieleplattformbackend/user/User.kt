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
        for (role in roles) {
            if (role == "author") {
                return true
            }
        }
        return false
    }
}
package com.example.spieleplattformbackend.auth.dto

data class LoginResponseDto(val token: String) {
    val type = "Bearer"
}
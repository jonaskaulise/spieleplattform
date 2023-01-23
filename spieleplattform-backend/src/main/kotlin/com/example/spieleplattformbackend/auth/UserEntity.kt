package com.example.spieleplattformbackend.auth

import jakarta.persistence.*

@Entity
data class UserEntity(
    @Column(unique = true)
    var email: String,
    var password: String,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null
) {}
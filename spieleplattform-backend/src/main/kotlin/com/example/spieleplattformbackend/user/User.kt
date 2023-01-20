package com.example.spieleplattformbackend.user

import jakarta.persistence.*

@Entity
data class User(
    @Column(unique = true)
    var username: String,
    var password: String,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null
) {}
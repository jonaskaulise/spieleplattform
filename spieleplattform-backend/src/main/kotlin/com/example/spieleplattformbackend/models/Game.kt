package com.example.spieleplattformbackend.models

import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "games")
class Game(
    @Column(nullable = false)
    var name: String,
    var releaseDate: Date,
    var developer: String,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?=null
) {
}
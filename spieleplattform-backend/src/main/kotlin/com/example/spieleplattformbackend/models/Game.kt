package com.example.spieleplattformbackend.models

import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "games")
class Game(
    @Column(nullable = false)
    var name: String,
    @Column(nullable = true)
    var releaseDate: Date,
    @Column(nullable = true)
    var developer: String,
    @Column(nullable = true)
    @OneToMany(mappedBy = "game")
    val ratings: Set<Rating>? = null,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?=null
) {
}
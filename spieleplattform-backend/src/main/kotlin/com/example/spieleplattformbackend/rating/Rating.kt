package com.example.spieleplattformbackend.rating

import com.example.spieleplattformbackend.game.Game
import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*

@Entity
class Rating(
    @Column(nullable = false)
    var ratingValue: Int,
    @Column(nullable = true)
    var comment: String,
    @ManyToOne
    @JsonBackReference
    var game: Game,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?=null
) {
}
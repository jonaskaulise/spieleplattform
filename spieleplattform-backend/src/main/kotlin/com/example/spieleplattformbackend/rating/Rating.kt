package com.example.spieleplattformbackend.rating

import com.example.spieleplattformbackend.game.Game
import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*

@Entity
data class Rating(
    var graphicRating: Int,
    var soundRating: Int,
    var addictionRating: Int,
    var actionRating: Int,
    var comment: String,
    var username: String,
    @ManyToOne
    @JsonBackReference
    var game: Game,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null
) {
}
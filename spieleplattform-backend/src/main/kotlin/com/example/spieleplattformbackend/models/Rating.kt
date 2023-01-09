package com.example.spieleplattformbackend.models

import jakarta.persistence.*

@Entity
@Table(name = "ratings")
class Rating(
    @Column(nullable = false)
    var ratingRange: Int,
    var comment: String,
    //var game: Game,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?=null
) {
}
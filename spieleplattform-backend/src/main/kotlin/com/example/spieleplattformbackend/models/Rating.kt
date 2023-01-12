package com.example.spieleplattformbackend.models

import jakarta.persistence.*

@Entity
class Rating(
    @Column(nullable = false)
    var ratingValue: Int,
    @Column(nullable = true)
    var comment: String,
    @ManyToOne
    var game: Game,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?=null
) {
}
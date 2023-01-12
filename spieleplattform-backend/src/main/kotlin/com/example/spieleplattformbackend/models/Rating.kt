package com.example.spieleplattformbackend.models

import jakarta.persistence.*

@Entity
@Table(name = "ratings")
class Rating(
    @Column(nullable = false)
    var ratingValue: Int,
    @Column(nullable = true)
    var comment: String,
    //var game: Game,
    @ManyToOne
    @JoinColumn(name="game_id", nullable=false)
    var game: Game,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?=null
) {
}
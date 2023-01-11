package com.example.spieleplattformbackend.models

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
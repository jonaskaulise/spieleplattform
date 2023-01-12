package com.example.spieleplattformbackend.game

import com.example.spieleplattformbackend.rating.Rating
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
import java.time.LocalDate

@Entity
class Game(
    @Column(nullable = false)
    var name: String,
    @Column(nullable = true)
    var releaseDate: LocalDate,
    @Column(nullable = true)
    var developer: String,
    @Column(nullable = true, length = 10000)
    var imgUrl: String,
    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
    @JsonManagedReference
    var ratings: List<Rating> = emptyList(),
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null
) {
}
package com.example.spieleplattformbackend.models

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
    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
    @JsonManagedReference
    var ratings: List<Rating> = emptyList(),
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null
) {
}
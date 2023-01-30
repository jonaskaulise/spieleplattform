package com.example.spieleplattformbackend.game

import com.example.spieleplattformbackend.gameConsole.GameConsole
import com.example.spieleplattformbackend.rating.Rating
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
import java.time.LocalDate

@Entity
class Game(
    var name: String,
    var releaseDate: LocalDate,
    var developer: String,
    @Column(length = 65535)
    var description: String,
    @Column(length = 65535)
    var imgUrl: String,
    @Column(length = 65535)
    var trailerUrl: String,
    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.MERGE])
    @JsonManagedReference
    var gameConsoles: MutableList<GameConsole> = mutableListOf(),
    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
    @JsonManagedReference
    var ratings: MutableList<Rating> = mutableListOf(),
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null
) {
    override fun toString(): String {
        var gameString = "Name: $name \r\nDeveloper: $developer \r\nRelease date: $releaseDate \r\n"
        for (rating in ratings) {
            gameString += "${rating.ratingValue}, ${rating.comment}\r\n"
        }
        return gameString
    }
}
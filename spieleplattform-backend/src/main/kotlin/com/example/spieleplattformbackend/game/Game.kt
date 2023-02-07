package com.example.spieleplattformbackend.game

import com.example.spieleplattformbackend.gameConsole.GameConsole
import com.example.spieleplattformbackend.rating.Rating
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
import java.time.LocalDate

@Entity
data class Game(
    var name: String,
    var releaseDate: LocalDate,
    var developer: String,
    var authorUsername: String,
    @Column(length = 65535)
    var description: String,
    @Column(length = 65535)
    var imageUrl: String,
    @Column(length = 65535)
    var youtubeId: String,
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
            gameString += "${rating.graphicRating}, ${rating.soundRating}, ${rating.addictionRating}, ${rating.actionRating}, ${rating.comment}, ${rating.username}\r\n"
        }
        return gameString
    }
}
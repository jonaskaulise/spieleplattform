package com.example.spieleplattformbackend.gameConsole

import com.example.spieleplattformbackend.game.Game
import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*

@Entity
data class GameConsole(
    var name: String,
    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.MERGE], mappedBy = "gameConsoles")
    @JsonBackReference
    var games: MutableList<Game> = mutableListOf(),
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null
)
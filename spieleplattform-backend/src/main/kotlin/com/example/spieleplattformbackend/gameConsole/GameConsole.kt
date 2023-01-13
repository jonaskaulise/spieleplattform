package com.example.spieleplattformbackend.gameConsole

import com.example.spieleplattformbackend.game.Game
import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*

@Entity
class GameConsole(
    var name: String,
    @ManyToMany
    @JsonBackReference
    var games: List<Game> = mutableListOf(),
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null
) {}
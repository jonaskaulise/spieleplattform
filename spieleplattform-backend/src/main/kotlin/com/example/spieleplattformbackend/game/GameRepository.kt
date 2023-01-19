package com.example.spieleplattformbackend.game

import com.example.spieleplattformbackend.gameConsole.GameConsole
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface GameRepository : CrudRepository<Game, Int> {
    fun findAllByDeveloperStartingWithOrDeveloperStartingWith(letterOne: String, letterTwo: String): List<Game>

    fun findFirstByNameStartingWith(letter: String): Game?

    fun findGameByName(name: String): Game?

    fun findGamesByGameConsolesContains(gameConsole: GameConsole): Iterable<Game>

    fun findGamesByNameContainsIgnoreCase(nameSearch: String): Iterable<Game>

    fun findGamesByGameConsolesContainsAndNameContainsIgnoreCase(
        gameConsole: GameConsole,
        nameSearch: String
    ): Iterable<Game>
}
package com.example.spieleplattformbackend.game

import com.example.spieleplattformbackend.gameConsole.GameConsole
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GameRepository : JpaRepository<Game, Int> {
    fun countByIdNotNull(): Long

    fun findAllByDeveloperStartingWithOrDeveloperStartingWith(letterOne: String, letterTwo: String): List<Game>

    fun findFirstByNameStartingWith(letter: String): Game?

    fun findGameByName(name: String): Game?

    fun findGameById(id: Int): Game?

    fun findGamesByIdNotNull(): Iterable<Game>

    fun findGamesByGameConsolesContains(gameConsole: GameConsole): Iterable<Game>

    fun findGamesByNameContainsIgnoreCase(nameSearch: String): Iterable<Game>

    fun findGamesByGameConsolesContainsAndNameContainsIgnoreCase(
        gameConsole: GameConsole,
        nameSearch: String
    ): Iterable<Game>
}
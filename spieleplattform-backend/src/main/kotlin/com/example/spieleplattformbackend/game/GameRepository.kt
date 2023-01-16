package com.example.spieleplattformbackend.game

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface GameRepository : JpaRepository<Game, Int> {
    fun countByIdNotNull(): Long

    @Query(value = "SELECT developer FROM games WHERE developer LIKE 'R%' OR developer LIKE 'N%'", nativeQuery = true)
    fun findDevelopersStartingWithRorNSQL(): List<String>

    fun findAllByDeveloperStartingWithOrDeveloperStartingWith(letterOne: String, letterTwo: String): List<Game>

    fun findFirstByNameStartingWith(letter: String): Game?

    fun findGameByName(name: String): Game?

    fun findGameById(id: Int): Game?

    fun findGamesByIdNotNull(): Iterable<Game>
}
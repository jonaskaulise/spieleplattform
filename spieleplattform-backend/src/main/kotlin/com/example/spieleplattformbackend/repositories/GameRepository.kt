package com.example.spieleplattformbackend.repositories

import com.example.spieleplattformbackend.models.Game
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface GameRepository : CrudRepository<Game, Int> {
    fun countByIdNotNull(): Long

    @Query(value = "SELECT developer FROM games WHERE developer LIKE 'R%' OR developer LIKE 'N%'", nativeQuery = true)
    fun findDevelopersStartingWithRorNSQL(): List<String>

    fun findAllByDeveloperStartingWithOrDeveloperStartingWith(letterOne: String, letterTwo: String): List<Game>

    fun findFirstByNameStartingWith(letter: String): Game?

    fun findGameByName(name: String): Game?

    fun findGameById(id: Int): Game?
}
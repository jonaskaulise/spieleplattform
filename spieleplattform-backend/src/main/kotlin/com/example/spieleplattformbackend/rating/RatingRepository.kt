package com.example.spieleplattformbackend.rating

import com.example.spieleplattformbackend.game.Game
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RatingRepository : CrudRepository<Rating, Int> {
    fun existsByGameAndUsername(game: Game, username: String): Boolean
}
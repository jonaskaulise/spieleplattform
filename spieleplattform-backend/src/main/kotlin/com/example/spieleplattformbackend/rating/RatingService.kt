package com.example.spieleplattformbackend.rating

import com.example.spieleplattformbackend.game.Game
import com.example.spieleplattformbackend.game.GameService
import com.example.spieleplattformbackend.user.UserService
import org.springframework.stereotype.Service

@Service
class RatingService(
    val ratingRepository: RatingRepository,
    val gameService: GameService,
    val userService: UserService
) {

    fun saveRating(ratingDTO: RatingDTO): Rating? {
        val game: Game = gameService.getGameById(ratingDTO.gameId) ?: return null
        val username = userService.getCurrentUsername() ?: return null
        if (ratingRepository.existsByGameAndUsername(game, username)) return null

        val rating = Rating(
            ratingDTO.graphicRating,
            ratingDTO.soundRating,
            ratingDTO.addictionRating,
            ratingDTO.actionRating,
            ratingDTO.comment,
            username,
            game
        )
        game.ratings.add(rating)
        ratingRepository.save(rating)
        return rating
    }
}
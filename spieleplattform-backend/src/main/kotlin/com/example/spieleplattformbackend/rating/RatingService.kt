package com.example.spieleplattformbackend.rating

import com.example.spieleplattformbackend.exceptions.BadRequestException
import com.example.spieleplattformbackend.game.Game
import com.example.spieleplattformbackend.game.GameService
import com.example.spieleplattformbackend.user.UserService
import org.springframework.stereotype.Service
import javax.naming.AuthenticationException

@Service
class RatingService(
    val ratingRepository: RatingRepository,
    val gameService: GameService,
    val userService: UserService
) {

    fun saveRating(addRatingDTO: AddRatingDTO): Rating {
        val username =
            userService.getCurrentUsername() ?: throw AuthenticationException("Couldn't read username from token.")

        if (!addRatingDTO.hasValidRatingValues()) throw BadRequestException("At least one Rating value is out of range.")
        val game: Game =
            gameService.getGameById(addRatingDTO.gameId) ?: throw BadRequestException("Game doesn't exist.")
        if (ratingRepository.existsByGameAndUsername(
                game,
                username
            )
        ) throw BadRequestException("User already submitted a rating for this game.")

        val rating = Rating(
            addRatingDTO.graphicRating,
            addRatingDTO.soundRating,
            addRatingDTO.addictionRating,
            addRatingDTO.actionRating,
            addRatingDTO.comment,
            username,
            game
        )
        game.ratings.add(rating)
        ratingRepository.save(rating)
        return rating
    }
}
package com.example.spieleplattformbackend.rating

import com.example.spieleplattformbackend.game.Game
import com.example.spieleplattformbackend.game.GameService
import com.example.spieleplattformbackend.user.UserService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate

class RatingServiceTest {

    val ratingRepository: RatingRepository = mockk()
    val gameService: GameService = mockk()
    val userService: UserService = mockk()
    val ratingService = RatingService(ratingRepository, gameService, userService)

    val game = Game(
        "Minecraft",
        LocalDate.of(2011, 11, 18),
        "Mojang Studios",
        "author1",
        "Only cubes",
        "/",
        "youtube",
        mutableListOf(),
        mutableListOf(),
        1
    )

    @Test
    fun `saveRating saves given AddRatingDTO as Rating`() {
        //given
        val addRatingDTO = AddRatingDTO(2, 3, 4, 5, "great game", 1)
        val username = "user1"
        val rating = Rating(2, 3, 4, 5, "great game", "user1", game)
        every { gameService.getGameById(1) } returns game
        every { userService.getCurrentUsername() } returns username
        every { ratingRepository.existsByGameAndUsername(any(), username) } returns false
        every { ratingRepository.save(any()) } returns rating

        //when
        val result = ratingService.saveRating(addRatingDTO)

        //then
        verify {
            gameService.getGameById(1)
            userService.getCurrentUsername()
            ratingRepository.existsByGameAndUsername(any(), username)
            ratingRepository.save(any())
        }
        assertEquals(result.graphicRating, rating.graphicRating)
        assertEquals(result.soundRating, rating.soundRating)
        assertEquals(result.addictionRating, rating.addictionRating)
        assertEquals(result.actionRating, rating.actionRating)
        assertEquals(result.comment, rating.comment)
        assertEquals(result.username, rating.username)
    }
}

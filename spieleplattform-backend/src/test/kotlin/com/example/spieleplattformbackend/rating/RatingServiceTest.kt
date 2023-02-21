package com.example.spieleplattformbackend.rating

import com.example.spieleplattformbackend.game.Game
import com.example.spieleplattformbackend.game.GameService
import com.example.spieleplattformbackend.user.UserService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
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
        "Only cubes",
        "/",
        "youtube",
        mutableListOf(),
        mutableListOf(),
        1
    )

    @Test
    fun `when saveRating with ratingDTO return rating`() {
        //given
        val ratingDTO = RatingDTO(2, 3, 4, 5, "great game", 1)
        val username = "user1"
        val rating = Rating(2, 3, 4, 5, "great game", "user1", game)
        every { gameService.getGameById(1) } returns game
        every { userService.getCurrentUsername() } returns username
        every { ratingRepository.existsByGameAndUsername(game, username) } returns false
        every { ratingRepository.save(rating) } returns rating

        //when
        val result = ratingService.saveRating(ratingDTO)

        //then
        verify {
            gameService.getGameById(1)
            userService.getCurrentUsername()
            ratingRepository.existsByGameAndUsername(game, username)
            ratingRepository.save(rating)
        }
        assertThat(result).isEqualTo(rating)
    }
}

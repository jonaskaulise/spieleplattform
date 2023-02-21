package com.example.spieleplattformbackend.game

import com.example.spieleplattformbackend.gameConsole.GameConsole
import com.example.spieleplattformbackend.gameConsole.GameConsoleRepository
import com.example.spieleplattformbackend.gameConsole.GameConsoleService
import com.example.spieleplattformbackend.rating.RatingRepository
import com.example.spieleplattformbackend.user.User
import com.example.spieleplattformbackend.user.UserService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.data.repository.findByIdOrNull
import java.time.LocalDate

class GameServiceTest {
    val gameRepository: GameRepository = mockk()
    val ratingRepository: RatingRepository = mockk()
    val gameConsoleRepository: GameConsoleRepository = mockk()
    val userService: UserService = mockk()
    val gameConsoleService: GameConsoleService = mockk()
    val gameService =
        GameService(gameRepository, ratingRepository, gameConsoleRepository, userService, gameConsoleService)

    val gameConsole = GameConsole("Xbox", mutableListOf(), 1)
    val game = Game(
        "Minecraft",
        LocalDate.of(2011, 11, 18),
        "Mojang Studios",
        "author1",
        "Minecraft is a game.",
        "https://www.minecraft.net/content/dam/games/minecraft/key-art/Games_Subnav_Minecraft-300x465.jpg",
        "MmB9b5njVbA",
        mutableListOf(gameConsole),
        mutableListOf()
    )
    val addUpdateGameDTO = AddUpdateGameDTO(
        "Minecraft",
        "Mojang Studios",
        LocalDate.of(2011, 11, 18),
        "Minecraft is a game.",
        "https://www.minecraft.net/content/dam/games/minecraft/key-art/Games_Subnav_Minecraft-300x465.jpg",
        "MmB9b5njVbA",
        mutableListOf(1)
    )
    val currentUser = User("author1", "author", "1", "author@1.de", mutableListOf("author"), "1")

    @Test
    fun whenGetGame_thenReturnGame() {
        //given
        every { gameRepository.findByIdOrNull(1) } returns game

        //when
        val result = gameService.getGameById(1)

        //then
        verify { gameRepository.findByIdOrNull(1) }
        assertEquals(game, result)
    }

    @Test
    fun `getAllGames returns games`() {
        //given
        every { gameRepository.findAll() } returns mutableListOf(game)

        //when
        val result = gameService.getAllGames()

        //then
        verify { gameRepository.findAll() }
        assertEquals(result, mutableListOf(game))
    }

    @Test
    fun `getGamesByGameConsoleId returns games whose console-list include console with given consoleId`() {
        //given
        every { gameConsoleRepository.findByIdOrNull(1) } returns gameConsole
        every { gameRepository.findGamesByGameConsolesContains(gameConsole) } returns mutableListOf(game)

        //when
        val result = gameService.getGamesByGameConsoleId(1)

        //then
        verify {
            gameConsoleRepository.findByIdOrNull(1)
            gameRepository.findGamesByGameConsolesContains(gameConsole)
        }
        assertEquals(result, mutableListOf(game))
    }

    @Test
    fun `getGamesByGameConsoleId returns empty list when no games console-list include console with given consoleId`() {
        //given
        every { gameConsoleRepository.findByIdOrNull(2) } returns gameConsole
        every { gameRepository.findGamesByGameConsolesContains(gameConsole) } returns mutableListOf()

        //when
        val result = gameService.getGamesByGameConsoleId(2)

        //then
        verify {
            gameConsoleRepository.findByIdOrNull(2)
            gameRepository.findGamesByGameConsolesContains(gameConsole)
        }
        assertEquals(result, mutableListOf<Game>())
    }

    @Test
    fun `getGamesByNameSearch returns all games whose names contain the nameSearch-string`() {
        //given
        every { gameRepository.findGamesByNameContainsIgnoreCase("m") } returns mutableListOf(game)

        //when
        val result = gameService.getGamesByNameSearch("m")

        //then
        verify { gameRepository.findGamesByNameContainsIgnoreCase("m") }
        assertEquals(result, mutableListOf(game))
    }

    @Test
    fun `getGamesByNameSearch returns empty list when the nameSearch-string isn't included in any game-name`() {
        //given
        every { gameRepository.findGamesByNameContainsIgnoreCase("q") } returns mutableListOf()

        //when
        val result = gameService.getGamesByNameSearch("q")

        //then
        verify { gameRepository.findGamesByNameContainsIgnoreCase("q") }
        assertEquals(result, mutableListOf<Game>())
    }

    @Test
    fun `getGamesByGameConsoleIdAndNameSearch returns games whose names contain given nameSearch-string and whose console-list include consoles with given consoleId`() {
        //given
        every { gameConsoleRepository.findByIdOrNull(1) } returns gameConsole
        every {
            gameRepository.findGamesByGameConsolesContainsAndNameContainsIgnoreCase(gameConsole, "m")
        } returns mutableListOf(game)

        //when
        val result = gameService.getGamesByGameConsoleIdAndNameSearch(1, "m")

        //then
        verify {
            gameConsoleRepository.findByIdOrNull(1)
            gameRepository.findGamesByGameConsolesContainsAndNameContainsIgnoreCase(gameConsole, "m")
        }
        assertEquals(result, mutableListOf(game))
    }

    @Test
    fun `getGamesByGameConsoleIdAndNameSearch returns empty list when no game-name contains given nameSearch-string or no games console-list includes console with given consoleId`() {
        //given
        every { gameConsoleRepository.findByIdOrNull(2) } returns gameConsole
        every {
            gameRepository.findGamesByGameConsolesContainsAndNameContainsIgnoreCase(gameConsole, "q")
        } returns mutableListOf()

        //when
        val result = gameService.getGamesByGameConsoleIdAndNameSearch(2, "q")

        //then
        verify {
            gameConsoleRepository.findByIdOrNull(2)
            gameRepository.findGamesByGameConsolesContainsAndNameContainsIgnoreCase(gameConsole, "q")
        }
        assertEquals(result, mutableListOf<Game>())
    }

    @Test
    fun `getGamesByOptionalConsoleIdAndNameSearch returns games whose names contain given nameSearch-string and whose console-list include consoles with given consoleId`() {
        //given
        every { gameConsoleRepository.findByIdOrNull(1) } returns gameConsole
        every {
            gameRepository.findGamesByGameConsolesContainsAndNameContainsIgnoreCase(gameConsole, "m")
        } returns mutableListOf(game)

        //when
        val result = gameService.getGamesByOptionalGameConsoleIdAndNameSearch(1, "m")

        //then
        verify {
            gameConsoleRepository.findByIdOrNull(1)
            gameRepository.findGamesByGameConsolesContainsAndNameContainsIgnoreCase(gameConsole, "m")
        }
        assertEquals(result, mutableListOf(game))
    }

    @Test
    fun `getGamesByOptionalConsoleIdAndNameSearch returns empty list when no game-name contains given nameSearch-string or no games console-list includes console with given consoleId`() {
        //given
        every { gameConsoleRepository.findByIdOrNull(2) } returns gameConsole
        every {
            gameRepository.findGamesByGameConsolesContainsAndNameContainsIgnoreCase(gameConsole, "q")
        } returns mutableListOf()

        //when
        val result = gameService.getGamesByOptionalGameConsoleIdAndNameSearch(2, "q")

        //then
        verify {
            gameConsoleRepository.findByIdOrNull(2)
            gameRepository.findGamesByGameConsolesContainsAndNameContainsIgnoreCase(gameConsole, "q")
        }
        assertEquals(result, mutableListOf<Game>())
    }

    @Test
    fun `saveGame saves given game if context is correct`() {
        //given
        every { userService.getCurrentUser() } returns currentUser
        every { gameRepository.existsByName(addUpdateGameDTO.name) } returns false
        every { gameConsoleService.getGameConsolesByGameConsoleIds(addUpdateGameDTO.gameConsoleIds) } returns mutableListOf(
            gameConsole
        )
        every { gameRepository.save(any()) } returns game

        //when
        val result = gameService.saveGame(addUpdateGameDTO)

        //then
        verify {
            userService.getCurrentUser()
            gameRepository.existsByName(addUpdateGameDTO.name)
            gameConsoleService.getGameConsolesByGameConsoleIds(addUpdateGameDTO.gameConsoleIds)
            gameRepository.save(any())
        }
        assertEquals(result.name, game.name)
        assertEquals(result.releaseDate, game.releaseDate)
        assertEquals(result.description, game.description)
        assertEquals(result.developer, game.developer)
        assertEquals(result.authorUsername, game.authorUsername)
        assertEquals(result.imageUrl, game.imageUrl)
        assertEquals(result.youtubeId, game.youtubeId)
    }

    @Test
    fun `updateGame updates game with given gameId by given game-data if context is correct`() {
        //given
        every { userService.getCurrentUser() } returns currentUser
        every { gameRepository.findByIdOrNull(1) } returns game
        every { gameConsoleService.getGameConsolesByGameConsoleIds(addUpdateGameDTO.gameConsoleIds) } returns mutableListOf(
            gameConsole
        )
        every { gameRepository.save(any()) } returns game

        //when
        val result = gameService.updateGame(1, addUpdateGameDTO)

        //then
        verify {
            userService.getCurrentUser()
            gameRepository.findByIdOrNull(1)
            gameConsoleService.getGameConsolesByGameConsoleIds(addUpdateGameDTO.gameConsoleIds)
            gameRepository.save(any())
        }
        assertEquals(result.name, game.name)
        assertEquals(result.releaseDate, game.releaseDate)
        assertEquals(result.description, game.description)
        assertEquals(result.developer, game.developer)
        assertEquals(result.authorUsername, game.authorUsername)
        assertEquals(result.imageUrl, game.imageUrl)
        assertEquals(result.youtubeId, game.youtubeId)
    }
}
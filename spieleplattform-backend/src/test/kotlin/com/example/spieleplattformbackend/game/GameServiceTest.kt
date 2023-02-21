package com.example.spieleplattformbackend.game

import com.example.spieleplattformbackend.gameConsole.GameConsole
import com.example.spieleplattformbackend.gameConsole.GameConsoleRepository
import com.example.spieleplattformbackend.rating.RatingRepository
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
    val gameService = GameService(gameRepository, ratingRepository, gameConsoleRepository)
    val gameConsole = GameConsole("Xbox")
    val game = Game(
        "Minecraft",
        LocalDate.of(2011, 11, 18),
        "Mojang Studios",
        "Minecraft ist ein Sandbox-Computerspiel, das ursprünglich vom schwedischen Programmierer Markus „Notch“ Persson und seinem dazu gegründeten Unternehmen Mojang entwickelt wurde. Mojang samt Spiel gehört seit September 2014 zu Microsoft. Minecraft erschien erstmals am 17. Mai 2009 als Early-Access-Titel für PC. In der Folge wurde Minecraft für diverse weitere Plattformen und Spielkonsolen veröffentlicht. Die meisten davon erhalten bis heute regelmäßig kostenfreie Aktualisierungen mit neuen Inhalten.",
        "https://www.minecraft.net/content/dam/games/minecraft/key-art/Games_Subnav_Minecraft-300x465.jpg",
        "MmB9b5njVbA"
    )

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
    fun `when getAllGames return games`() {
        //given
        every { gameRepository.findAll() } returns mutableListOf(game)

        //when
        val result = gameService.getAllGames()

        //then
        verify { gameRepository.findAll() }
        assertEquals(result, mutableListOf(game))
    }

    @Test
    fun `when getGamesByGameConsoleId with id=1 return games`() {
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
    fun `when getGamesByGameConsoleId with id=2 return empty list`() {
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
    fun `when getGamesByNameSearch with m return list with minecraft`() {
        //given
        every { gameRepository.findGamesByNameContainsIgnoreCase("m") } returns mutableListOf(game)

        //when
        val result = gameService.getGamesByNameSearch("m")

        //then
        verify { gameRepository.findGamesByNameContainsIgnoreCase("m") }
        assertEquals(result, mutableListOf(game))
    }

    @Test
    fun `when getGamesByNameSearch with q return empty list`() {
        //given
        every { gameRepository.findGamesByNameContainsIgnoreCase("q") } returns mutableListOf()

        //when
        val result = gameService.getGamesByNameSearch("q")

        //then
        verify { gameRepository.findGamesByNameContainsIgnoreCase("q") }
        assertEquals(result, mutableListOf<Game>())
    }

    @Test
    fun `when getGamesByGameConsoleIdAndNameSearch with id=1 and m return minecraft`() {
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
    fun `when getGamesByGameConsoleIdAndNameSearch with id=2 and q return empty list`() {
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
    fun `when getGamesByOptionalConsoleIdAndNameSearch with id=1 and m return minecraft`() {
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
    fun `when getGamesByOptionalConsoleIdAndNameSearch with id=2 and q return empty list`() {
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
}
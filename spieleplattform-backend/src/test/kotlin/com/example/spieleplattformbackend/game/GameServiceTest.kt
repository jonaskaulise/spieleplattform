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
    fun whenGetGames_thenReturnGames() {
        //given
        every { gameRepository.findAll() } returns mutableListOf(game)

        //when
        val result = gameService.getAllGames()

        //then
        verify { gameRepository.findAll() }
        assertEquals(result, mutableListOf(game))
    }

    @Test
    fun whenGetGamesByGameConsoleId_thenReturnGames() {
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
    fun whenGetGamesByNameSearch_thenReturnGames() {
        //given
        every { gameRepository.findGamesByNameContainsIgnoreCase("m") } returns mutableListOf(game)

        //when
        val result = gameService.getGamesByNameSearch("m")

        //then
        verify { gameRepository.findGamesByNameContainsIgnoreCase("m") }
        assertEquals(result, mutableListOf(game))
    }

    @Test
    fun whenGetGamesByConsoleIdAndNameSearch_thenReturnGames() {
        //given
        every { gameConsoleRepository.findByIdOrNull(1) } returns gameConsole
        every {
            gameRepository.findGamesByGameConsolesContainsAndNameContainsIgnoreCase(gameConsole, "m")
        } returns mutableListOf(game)

        //when
        val result = gameService.getGamesByConsoleIdAndNameSearch(1, "m")

        //then
        verify {
            gameConsoleRepository.findByIdOrNull(1)
            gameRepository.findGamesByGameConsolesContainsAndNameContainsIgnoreCase(gameConsole, "m")
        }
        assertEquals(result, mutableListOf(game))
    }
}
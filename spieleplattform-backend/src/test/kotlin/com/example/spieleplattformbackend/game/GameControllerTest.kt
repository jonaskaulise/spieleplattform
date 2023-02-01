package com.example.spieleplattformbackend.game

import com.example.spieleplattformbackend.auth.AuthService
import com.example.spieleplattformbackend.gameConsole.GameConsoleService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import java.time.LocalDate

@WebMvcTest
class GameControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    lateinit var gameService: GameService

    @MockkBean
    lateinit var gameConsoleService: GameConsoleService

    @MockkBean
    lateinit var authService: AuthService

    val game = Game(
        "Minecraft",
        LocalDate.of(2011, 11, 18),
        "Mojang Studios",
        "Minecraft ist ein Sandbox-Computerspiel, das ursprünglich vom schwedischen Programmierer Markus „Notch“ Persson und seinem dazu gegründeten Unternehmen Mojang entwickelt wurde. Mojang samt Spiel gehört seit September 2014 zu Microsoft. Minecraft erschien erstmals am 17. Mai 2009 als Early-Access-Titel für PC. In der Folge wurde Minecraft für diverse weitere Plattformen und Spielkonsolen veröffentlicht. Die meisten davon erhalten bis heute regelmäßig kostenfreie Aktualisierungen mit neuen Inhalten.",
        "https://www.minecraft.net/content/dam/games/minecraft/key-art/Games_Subnav_Minecraft-300x465.jpg",
        "MmB9b5njVbA"
    )

    @Test
    fun when_gamesOfGameConsoleId_withoutParameters() {
        //given
        every { gameService.getAllGames() } returns mutableListOf(game)

        //when//then
        mockMvc.get("/games")
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$[0].name").value("Minecraft")
            }
    }

    @Test
    fun when_gamesOfGameConsoleId_withGameConsoleId_1() {
        //given
        every { gameService.getGamesByGameConsoleId(1) } returns mutableListOf(game)

        //when//then
        mockMvc.get("/games?gameConsoleId=1")
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$[0].name").value("Minecraft")
            }
    }

    @Test
    fun when_gamesOfGameConsoleId_withNameSearch_m() {
        //given
        every { gameService.getGamesByNameSearch("m") } returns mutableListOf(game)

        //when//then
        mockMvc.get("/games?nameSearch=m")
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$[0].name").value("Minecraft")
            }
    }

    @Test
    fun when_gamesOfGameConsoleId_withGameConsoleId_1_withNameSearch_m() {
        //given
        every { gameService.getGamesByOptionalGameConsoleIdAndNameSearch(1, "m") } returns mutableListOf(game)

        //when//then
        mockMvc.get("/games?gameConsoleId=1&nameSearch=m")
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$[0].name").value("Minecraft")
            }
    }

    @Test
    fun when_game() {
        //given
        every { gameService.getGameById(1) } returns game

        //when//then
        mockMvc.get("/games/1")
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.name").value("Minecraft")
            }
    }
}
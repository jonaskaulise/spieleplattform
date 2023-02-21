package com.example.spieleplattformbackend.gameConsole

import com.example.spieleplattformbackend.auth.AuthService
import com.example.spieleplattformbackend.game.GameService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest
class GameConsoleControllerTest(@Autowired val mockMvc: MockMvc) {
    @MockkBean
    lateinit var gameConsoleService: GameConsoleService

    @MockkBean
    lateinit var gameService: GameService

    @MockkBean
    lateinit var authService: AuthService

    val gameConsole = GameConsole("Xbox")

    @Test
    fun when_gameConsoles_withoutParameters() {
        //given
        every { gameConsoleService.getGameConsoles() } returns mutableListOf(gameConsole)

        //when//then
        mockMvc.get("/gameConsoles")
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                MockMvcResultMatchers.jsonPath("$[0].name").value("Xbox")
            }
    }
}
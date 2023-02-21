package com.example.spieleplattformbackend.gameConsole

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GameConsoleServiceTest {
    var gameConsoleRepository: GameConsoleRepository = mockk()
    var gameConsoleService: GameConsoleService = GameConsoleService(gameConsoleRepository)

    val gameConsole = GameConsole("Xbox")

    @Test
    fun `when getGameConsoles return list with Xbox`() {
        //given
        every { gameConsoleRepository.findAll() } returns mutableListOf(gameConsole)

        //when
        val result = gameConsoleService.getGameConsoles()

        //then
        verify { gameConsoleRepository.findAll() }
        assertEquals(result, mutableListOf(gameConsole))
    }
}
package com.example.spieleplattformbackend.gameConsole

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class GameConsoleServiceTest {
    var gameConsoleRepository: GameConsoleRepository = mockk()
    var gameConsoleService: GameConsoleService = GameConsoleService(gameConsoleRepository)

    val console1 = GameConsole("Console1", mutableListOf(), 1)
    val console2 = GameConsole("Console2", mutableListOf(), 2)

    @Test
    fun `getGameConsoles returns all gameConsoles`() {
        //given
        every { gameConsoleRepository.findAll() } returns mutableListOf(console1)

        //when
        val result = gameConsoleService.getGameConsoles()

        //then
        verify { gameConsoleRepository.findAll() }
        assertEquals(result, mutableListOf(console1))
    }

    @Test
    fun `getGameConsolesByGameConsoleIds returns gameConsoles by their gameConsoleIds`() {
        //given
        val gameConsoleIds = mutableListOf(1, 2)
        val gameConsoles = mutableListOf(console1, console2)
        every { gameConsoleRepository.findAllById(gameConsoleIds) } returns gameConsoles

        //when
        val result = gameConsoleService.getGameConsolesByGameConsoleIds(gameConsoleIds)

        //then
        verify { gameConsoleRepository.findAllById(gameConsoleIds) }
        assertEquals(result, gameConsoles)
    }

    @Test
    fun `getGameConsolesByGameConsoleIds returns null if at least one given consoleId isn't assigned`() {
        //given
        val gameConsoleIds = mutableListOf(1, 3)
        val gameConsoles = mutableListOf(console1)
        every { gameConsoleRepository.findAllById(gameConsoleIds) } returns gameConsoles

        //when
        val result = gameConsoleService.getGameConsolesByGameConsoleIds(gameConsoleIds)

        //then
        verify { gameConsoleRepository.findAllById(gameConsoleIds) }
        assertNull(result)
    }
}


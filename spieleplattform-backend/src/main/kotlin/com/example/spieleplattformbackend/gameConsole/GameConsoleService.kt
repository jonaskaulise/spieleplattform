package com.example.spieleplattformbackend.gameConsole

import org.springframework.stereotype.Service

@Service
class GameConsoleService(val gameConsoleRepository: GameConsoleRepository) {
    fun getGameConsoles(): Iterable<GameConsole> {
        return gameConsoleRepository.findAll()
    }

    fun getGameConsolesByGameConsoleIds(gameConsoleIds: MutableList<Int>): MutableList<GameConsole>? {
        val gameConsoles = gameConsoleRepository.findAllById(gameConsoleIds).toMutableList()
        return if (gameConsoles.size == gameConsoleIds.size) {
            gameConsoles
        } else {
            null
        }
    }
}
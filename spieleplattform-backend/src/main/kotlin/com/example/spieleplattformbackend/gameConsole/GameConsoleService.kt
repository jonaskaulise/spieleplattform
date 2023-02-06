package com.example.spieleplattformbackend.gameConsole

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class GameConsoleService(val gameConsoleRepository: GameConsoleRepository) {
    fun getGameConsoles(): Iterable<GameConsole> {
        return gameConsoleRepository.findAll()
    }

    fun getGameConsolesByGameConsoleIds(gameConsoleIds: MutableList<Int>): MutableList<GameConsole>? {
        val gameConsoleList = mutableListOf<GameConsole>()
        for (id in gameConsoleIds) {
            val gameConsole = gameConsoleRepository.findByIdOrNull(id) ?: return null
            gameConsoleList.add(gameConsole)
        }
        return gameConsoleList
    }
}
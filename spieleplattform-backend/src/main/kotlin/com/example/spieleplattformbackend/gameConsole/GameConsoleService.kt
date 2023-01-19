package com.example.spieleplattformbackend.gameConsole

import org.springframework.stereotype.Service

@Service
class GameConsoleService(val gameConsoleRepository: GameConsoleRepository) {
    fun getGameConsoles(): Iterable<GameConsole> {
        return gameConsoleRepository.findAll()
    }
}
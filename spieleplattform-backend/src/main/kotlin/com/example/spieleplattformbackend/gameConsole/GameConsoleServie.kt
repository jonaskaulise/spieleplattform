package com.example.spieleplattformbackend.gameConsole

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GameConsoleService(@Autowired val gameConsoleRepository: GameConsoleRepository) {
    fun getGameConsoles(): Iterable<GameConsole> {
        return gameConsoleRepository.findGameConsolesByIdNotNull()
    }
}
package com.example.spieleplattformbackend.gameConsole

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GameConsoleController(@Autowired var gameConsoleService: GameConsoleService) {

    @GetMapping("/gameConsoles")
    fun gameConsoles(): Iterable<GameConsole> {
        return gameConsoleService.getGameConsoles()
    }
}
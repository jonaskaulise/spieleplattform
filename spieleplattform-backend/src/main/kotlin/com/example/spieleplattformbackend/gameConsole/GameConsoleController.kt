package com.example.spieleplattformbackend.gameConsole

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/gameConsoles")
class GameConsoleController(@Autowired var gameConsoleService: GameConsoleService) {

    @GetMapping("")
    fun gameConsoles(): Iterable<GameConsole> {
        return gameConsoleService.getGameConsoles()
    }
}
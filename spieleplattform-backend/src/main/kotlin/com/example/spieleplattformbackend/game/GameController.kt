package com.example.spieleplattformbackend.game

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/games")
class GameController(@Autowired var gameService: GameService) {

    @GetMapping("")
    fun gamesByConsoleIdAndNameSearch(
        @RequestParam(required = false, defaultValue = "") gameConsoleId: Int?,
        @RequestParam(required = false, defaultValue = "") nameSearch: String
    ): Iterable<Game> {
        return gameService.getGamesByOptionalGameConsoleIdAndNameSearch(gameConsoleId, nameSearch)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    @GetMapping("/{id}")
    fun game(@PathVariable id: Int): Game {
        return gameService.getGameById(id)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
}
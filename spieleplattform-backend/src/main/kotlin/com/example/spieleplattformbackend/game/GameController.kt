package com.example.spieleplattformbackend.game

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
class GameController(@Autowired var gameService: GameService) {

    @GetMapping("/games")
    fun gamesOfGameConsoleId(@RequestParam(required = false, defaultValue = "0") gameConsoleId: Int): Iterable<Game> {
        return if (gameConsoleId == 0)
            gameService.getAllGames()
        else
            gameService.getGamesByGameConsoleId(gameConsoleId)
    }

    @GetMapping("/games/{id}")
    fun game(@PathVariable id: Int): Game {
        return gameService.getGameById(id)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
}
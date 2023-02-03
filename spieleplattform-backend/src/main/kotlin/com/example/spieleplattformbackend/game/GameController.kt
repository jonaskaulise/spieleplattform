package com.example.spieleplattformbackend.game

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/games")
class GameController(@Autowired var gameService: GameService) {

    @GetMapping("")
    fun gamesOfGameConsoleId(
        @RequestParam(required = false, defaultValue = "0") gameConsoleId: Int,
        @RequestParam(required = false, defaultValue = "") nameSearch: String
    ): Iterable<Game> {
        return if (gameConsoleId == 0 && nameSearch == "")
            gameService.getAllGames()
        else if (gameConsoleId != 0 && nameSearch == "")
            gameService.getGamesByGameConsoleId(gameConsoleId)
        else if (gameConsoleId == 0)
            gameService.getGamesByNameSearch(nameSearch)
        else
            gameService.getGamesByConsoleIdAndNameSearch(gameConsoleId, nameSearch)
    }

    @GetMapping("/{id}")
    fun game(@PathVariable id: Int): Game {
        return gameService.getGameById(id)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
}
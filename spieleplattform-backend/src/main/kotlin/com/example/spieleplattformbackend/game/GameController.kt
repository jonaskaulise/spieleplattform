package com.example.spieleplattformbackend.game

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

//@CrossOrigin("http://localhost:3000", maxAge = 3600)
@RestController
class GameController(@Autowired var gameService: GameService) {

    @GetMapping("/game")
    fun games(): Iterable<Game> {
        return gameService.getAllGames()
    }

    @GetMapping("/game/{id}")
    fun game(@PathVariable id: Int): Game {
        return gameService.findGameWithId(id)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
}
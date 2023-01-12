package com.example.spieleplattformbackend.controller

import com.example.spieleplattformbackend.models.Game
import com.example.spieleplattformbackend.services.GameService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
class GameController(@Autowired var gameService: GameService) {
    @GetMapping("/game/{id}")
    fun game(@PathVariable id: Int): Game {
        return gameService.findGameWithId(id)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

}
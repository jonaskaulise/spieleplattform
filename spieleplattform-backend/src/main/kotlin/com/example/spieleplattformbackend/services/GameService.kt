package com.example.spieleplattformbackend.services

import com.example.spieleplattformbackend.models.Game
import com.example.spieleplattformbackend.repositories.GameRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

// bla
@Service
class GameService(@Autowired val gameRepository: GameRepository) {
    fun insertExampleGames() {
        gameRepository.save(Game("Minecraft", Date(111, 10, 18), "Mojang Studios"))
        gameRepository.save(Game("Die Sims 4", Date(114, 8, 2), "Maxis"))
        gameRepository.save(Game("Grand Theft Auto V", Date(113, 8, 17), "Rockstar North"))
        gameRepository.save(Game("Super Mario Bros.", Date(85, 8, 13), "Nintendo Research"))
        gameRepository.save(Game("Tetris", Date(84, 5, 6), "Alexei Paschitnow"))
    }

    // bliblablub
    fun getGamesCount() : Long {
        return gameRepository.countByIdNotNull()
    }

    fun findFirstGameStartingWithM() : Game? {
        return gameRepository.findFirstByNameStartingWith("o")
    }

    fun findDevelopersStartingWithRorN() : List<String> {
        //return gameRepository.findDevelopersStartingWithRorNSQL()
        val games = gameRepository.findAllByDeveloperStartingWithOrDeveloperStartingWith("R", "N")

        return games.map { game: Game -> game.developer }
    }
}
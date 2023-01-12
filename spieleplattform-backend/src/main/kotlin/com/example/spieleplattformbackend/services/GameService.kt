package com.example.spieleplattformbackend.services

import com.example.spieleplattformbackend.models.Game
import com.example.spieleplattformbackend.models.Rating
import com.example.spieleplattformbackend.repositories.GameRepository
import com.example.spieleplattformbackend.repositories.RatingRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class GameService(@Autowired val gameRepository: GameRepository, @Autowired val ratingRepository: RatingRepository) {
    fun insertExampleGames() {
        //minecraft
        val minecraft = Game("Minecraft", LocalDate.of(2011, 11, 18), "Mojang Studios")
        minecraft.ratings = listOf(
            Rating(5, "Best Game ever", minecraft),
            Rating(4, "Good, but only Blocks", minecraft),
            Rating(2, "Game is ok, but i dont like the graphics", minecraft)
        )
        gameRepository.save(minecraft)
        //sims
        val sims = Game("Die Sims 4", LocalDate.of(2014, 12, 18), "Maxis")
        sims.ratings = listOf(
            Rating(5, "I love it", sims)
        )
        gameRepository.save(sims)
        //GTA
        val gta = Game("Grand Theft Auto V", LocalDate.of(2013, 9, 17), "Rockstar North")
        gta.ratings = listOf(
            Rating(5, "Awesome game, you can do whatever you want!", gta),
            Rating(3, "Good game, but to much violence", gta)
        )
        gameRepository.save(gta)
        //SuperMario
        val superMario = Game("Super Mario Bros.", LocalDate.of(1985, 9, 13), "Nintendo Research")
        superMario.ratings = listOf(
            Rating(5, "I love this game", superMario)
        )
        gameRepository.save(superMario)
        //Tetris
        val tetris = Game("Tetris", LocalDate.of(1984, 6, 6), "Alexei Paschitnow")
        gameRepository.save(tetris)

        val games = arrayOf(minecraft, sims, gta, superMario, tetris)
        for (game in games) {
            for (rating in game.ratings) {
                ratingRepository.save(rating)
            }
        }
    }

    fun printGameWithName(name: String) {
        val game = gameRepository.findGameByName(name) ?: return

        println("${game.name}, ${game.developer}, ${game.releaseDate}")
        println("Ratings: ")
        for (ratings in game.ratings) {
            println("${ratings.ratingValue}, ${ratings.comment}")
        }
    }

    fun getGamesCount(): Long {
        return gameRepository.countByIdNotNull()
    }

    fun findFirstGameStartingWithM(): Game? {
        return gameRepository.findFirstByNameStartingWith("M")
    }

    fun findDevelopersStartingWithRorN(): List<String> {
        //return gameRepository.findDevelopersStartingWithRorNSQL()
        val games = gameRepository.findAllByDeveloperStartingWithOrDeveloperStartingWith("R", "N")

        return games.map { game: Game -> game.developer }
    }
}
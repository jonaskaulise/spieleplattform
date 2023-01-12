package com.example.spieleplattformbackend.game

import com.example.spieleplattformbackend.rating.Rating
import com.example.spieleplattformbackend.rating.RatingRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class GameService(@Autowired val gameRepository: GameRepository, @Autowired val ratingRepository: RatingRepository) {
    fun insertExampleGames() {
        //minecraft
        val minecraft = Game(
            "Minecraft",
            LocalDate.of(2011, 11, 18),
            "Mojang Studios",
            "https://www.minecraft.net/content/dam/games/minecraft/key-art/Games_Subnav_Minecraft-300x465.jpg"
        )
        minecraft.ratings = listOf(
            Rating(5, "Best Game ever", minecraft),
            Rating(4, "Good, but only Blocks", minecraft),
            Rating(2, "Game is ok, but i dont like the graphics", minecraft)
        )
        gameRepository.save(minecraft)
        //sims
        val sims = Game(
            "Die Sims 4",
            LocalDate.of(2014, 12, 18),
            "Maxis",
            "https://simscommunity.info/wp-content/uploads/2019/07/boxart.jpg"
        )
        sims.ratings = listOf(
            Rating(5, "I love it", sims)
        )
        gameRepository.save(sims)
        //GTA
        val gta = Game(
            "Grand Theft Auto V",
            LocalDate.of(2013, 9, 17),
            "Rockstar North",
            "https://media.rockstargames.com/rockstargames/img/global/news/upload/actual_1364906194.jpg"
        )
        gta.ratings = listOf(
            Rating(5, "Awesome game, you can do whatever you want!", gta),
            Rating(3, "Good game, but to much violence", gta)
        )
        gameRepository.save(gta)
        //SuperMario
        val superMario = Game(
            "Super Mario Bros.",
            LocalDate.of(1985, 9, 13),
            "Nintendo Research",
            "https://image.konsolenkost.de/item/images/9074377/full/nes-super-mario-bros-1-alternatives-cover.jpg"
        )
        superMario.ratings = listOf(
            Rating(5, "I love this game", superMario)
        )
        gameRepository.save(superMario)
        //Tetris
        val tetris = Game(
            "Tetris",
            LocalDate.of(1984, 6, 6),
            "Alexei Paschitnow",
            "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/95b13597-76f2-4029-b752-a03e008ee525/d5zi7e0-06488650-7059-49bc-82ee-27bcc5e6cba9.jpg/v1/fill/w_1024,h_1455,q_75,strp/tetris_original_cover_by_zalmay_d5zi7e0-fullview.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7ImhlaWdodCI6Ijw9MTQ1NSIsInBhdGgiOiJcL2ZcLzk1YjEzNTk3LTc2ZjItNDAyOS1iNzUyLWEwM2UwMDhlZTUyNVwvZDV6aTdlMC0wNjQ4ODY1MC03MDU5LTQ5YmMtODJlZS0yN2JjYzVlNmNiYTkuanBnIiwid2lkdGgiOiI8PTEwMjQifV1dLCJhdWQiOlsidXJuOnNlcnZpY2U6aW1hZ2Uub3BlcmF0aW9ucyJdfQ.yja-b5ICUkd1QfY0s6CuM1Tb-qjC8sKJzz_GNEex9Lk"
        )
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

    fun findGameWithId(id: Int): Game? {
        return gameRepository.findGameById(id)
    }

    fun getAllGames(): Iterable<Game> {
        return gameRepository.findGamesByIdNotNull()
    }
}
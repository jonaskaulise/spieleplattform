package com.example.spieleplattformbackend.game

import com.example.spieleplattformbackend.gameConsole.GameConsole
import com.example.spieleplattformbackend.gameConsole.GameConsoleRepository
import com.example.spieleplattformbackend.rating.Rating
import com.example.spieleplattformbackend.rating.RatingRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDate

@Service
class GameService(
    @Autowired val gameRepository: GameRepository,
    @Autowired val ratingRepository: RatingRepository,
    @Autowired val gameConsoleRepository: GameConsoleRepository
) {
    fun insertExampleData() {
        //consoles
        val xbox = GameConsole("Xbox")
        val xbox360 = GameConsole("Xbox 360")
        val xboxOne = GameConsole("Xbox One")
        val ps1 = GameConsole("Playstation")
        val ps2 = GameConsole("Playstation 2")
        val ps3 = GameConsole("Playstation 3")
        val ps4 = GameConsole("Playstation 4")
        val ps5 = GameConsole("Playstation 5")
        val psp = GameConsole("Playstation Portable")
        val psv = GameConsole("Playstation Vita")
        val gameCube = GameConsole("Nintendo GameCube")
        val wii = GameConsole("Nintendo Wii")
        val wiiU = GameConsole("Nintendo Wii U")
        val switch = GameConsole("Nintendo Switch")
        val gameBoy = GameConsole("Nintendo Game Boy")
        val gameBoyAdvance = GameConsole("Nintendo Game Boy Advance")
        val nintendoDS = GameConsole("Nintendo DS")
        val nintendo3DS = GameConsole("Nintendo 3DS")
        val gameConsoles = arrayOf(
            xbox, xbox360, xboxOne, ps1, ps2, ps3, ps4, ps5, psp, psv,
            gameCube, wii, wiiU, switch, gameBoy, gameBoyAdvance, nintendoDS, nintendo3DS
        )


        //minecraft
        val minecraft = Game(
            "Minecraft",
            LocalDate.of(2011, 11, 18),
            "Mojang Studios",
            "https://www.minecraft.net/content/dam/games/minecraft/key-art/Games_Subnav_Minecraft-300x465.jpg"
        )
        minecraft.ratings.add(Rating(5, "Best Game ever", minecraft))
        minecraft.ratings.add(Rating(4, "Good, but only Blocks", minecraft))
        minecraft.ratings.add(Rating(2, "Game is ok, but i dont like the graphics", minecraft))
        minecraft.gameConsoles = mutableListOf(nintendo3DS, switch, ps3, ps4, psv, wiiU, xbox360, xboxOne)
        nintendo3DS.games.add(minecraft)
        switch.games.add(minecraft)
        ps3.games.add(minecraft)
        ps4.games.add(minecraft)
        psv.games.add(minecraft)
        wiiU.games.add(minecraft)
        xbox360.games.add(minecraft)
        xboxOne.games.add(minecraft)

        //Sims 4
        val sims4 = Game(
            "Die Sims 4",
            LocalDate.of(2014, 12, 18),
            "Maxis",
            "https://simscommunity.info/wp-content/uploads/2019/07/boxart.jpg"
        )
        sims4.ratings.add(Rating(5, "I love it", sims4))
        sims4.gameConsoles = mutableListOf(ps4, xboxOne)
        ps4.games.add(sims4)
        xboxOne.games.add(sims4)

        //GTA 5
        val gta5 = Game(
            "Grand Theft Auto V",
            LocalDate.of(2013, 9, 17),
            "Rockstar North",
            "https://media.rockstargames.com/rockstargames/img/global/news/upload/actual_1364906194.jpg"
        )
        gta5.ratings.add(Rating(5, "Awesome game, you can do whatever you want!", gta5))
        gta5.ratings.add(Rating(3, "Good game, but to much violence", gta5))
        gta5.gameConsoles = mutableListOf(xbox360, xboxOne, ps3, ps4, ps5)
        xbox360.games.add(gta5)
        xboxOne.games.add(gta5)
        ps3.games.add(gta5)
        ps4.games.add(gta5)
        ps5.games.add(gta5)

        //SuperMario
        val superMario1 = Game(
            "Super Mario Bros.",
            LocalDate.of(1985, 9, 13),
            "Nintendo Research",
            "https://image.konsolenkost.de/item/images/9074377/full/nes-super-mario-bros-1-alternatives-cover.jpg"
        )
        superMario1.ratings.add(Rating(5, "I love this game", superMario1))
        superMario1.gameConsoles = mutableListOf(switch, wii)
        switch.games.add(superMario1)
        wii.games.add(superMario1)

        //Tetris
        val tetris = Game(
            "Tetris",
            LocalDate.of(1984, 6, 6),
            "Alexei Paschitnow",
            "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/95b13597-76f2-4029-b752-a03e008ee525/d5zi7e0-06488650-7059-49bc-82ee-27bcc5e6cba9.jpg/v1/fill/w_1024,h_1455,q_75,strp/tetris_original_cover_by_zalmay_d5zi7e0-fullview.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7ImhlaWdodCI6Ijw9MTQ1NSIsInBhdGgiOiJcL2ZcLzk1YjEzNTk3LTc2ZjItNDAyOS1iNzUyLWEwM2UwMDhlZTUyNVwvZDV6aTdlMC0wNjQ4ODY1MC03MDU5LTQ5YmMtODJlZS0yN2JjYzVlNmNiYTkuanBnIiwid2lkdGgiOiI8PTEwMjQifV1dLCJhdWQiOlsidXJuOnNlcnZpY2U6aW1hZ2Uub3BlcmF0aW9ucyJdfQ.yja-b5ICUkd1QfY0s6CuM1Tb-qjC8sKJzz_GNEex9Lk"
        )
        tetris.gameConsoles = mutableListOf(gameBoy)
        gameBoy.games.add(tetris)

        //WiiSports
        val wiiSports = Game(
            "Wii Sports",
            LocalDate.of(2006, 11, 19),
            "Electronic Arts",
            "https://www.nintendofans.de/cover/Big_Cover_127788.jpg"
        )
        wiiSports.gameConsoles = mutableListOf(wii)
        wii.games.add(wiiSports)

        //Mario Kart 8
        val marioKart8 = Game(
            "Mario Kart 8 Deluxe",
            LocalDate.of(2014, 5, 24),
            "Nintendo",
            "https://s3.gaming-cdn.com/images/products/2615/orig-fallback-v1/mario-kart-8-deluxe-switch-switch-spiel-nintendo-eshop-europe-cover.jpg"
        )
        marioKart8.gameConsoles = mutableListOf(wiiU, switch)
        wiiU.games.add(marioKart8)
        switch.games.add(marioKart8)

        //Overwatch
        val overwatch = Game(
            "Overwatch",
            LocalDate.of(2016, 5, 24),
            "Blizzard Entertainment",
            "https://www.productcodes.de/wp-content/uploads/2021/01/Overwatch.jpg"
        )
        //gameRepository.save(overwatch)
        overwatch.gameConsoles = mutableListOf(xboxOne, ps4, switch)
        xboxOne.games.add(overwatch)
        ps4.games.add(overwatch)
        switch.games.add(overwatch)

        //Red Dead Redemption 2
        val redDeadRedemption2 = Game(
            "Read Dead Redemption 2",
            LocalDate.of(2018, 10, 26),
            "Rockstar Games",
            "https://image.api.playstation.com/cdn/UP1004/CUSA03041_00/Hpl5MtwQgOVF9vJqlfui6SDB5Jl4oBSq.png"
        )
        redDeadRedemption2.gameConsoles = mutableListOf(xboxOne, ps4)
        xboxOne.games.add(redDeadRedemption2)
        ps4.games.add(redDeadRedemption2)

        //Call of Duty: Black Ops
        val codBlackOps = Game(
            "Call of Duty: Black Ops",
            LocalDate.of(2010, 9, 9),
            "Activision",
            "https://static.posters.cz/image/750/poster/call-of-duty-black-ops-cover-i8700.jpg"
        )
        codBlackOps.gameConsoles = mutableListOf(xbox360, xboxOne, ps3, wii, nintendoDS)
        xbox360.games.add(codBlackOps)
        xboxOne.games.add(codBlackOps)
        ps3.games.add(codBlackOps)
        wii.games.add(codBlackOps)
        nintendoDS.games.add(codBlackOps)

        //Insert objects to database
        for (gameConsole in gameConsoles)
            gameConsoleRepository.save(gameConsole)
        val games = arrayOf(
            minecraft,
            sims4,
            gta5,
            superMario1,
            tetris,
            wiiSports,
            marioKart8,
            overwatch,
            redDeadRedemption2,
            codBlackOps
        )
        for (game in games) {
            gameRepository.save(game)
            for (rating in game.ratings) {
                ratingRepository.save(rating)
            }
        }
    }

    fun printGameWithName(name: String) {
        val game = gameRepository.findGameByName(name) ?: return
        println(game.toString())
    }

    fun getGamesCount(): Long = gameRepository.countByIdNotNull()

    fun findFirstGameStartingWithM(): Game? = gameRepository.findFirstByNameStartingWith("M")

    fun findDevelopersStartingWithRorN(): List<String> {
        val games = gameRepository.findAllByDeveloperStartingWithOrDeveloperStartingWith("R", "N")

        return games.map { game: Game -> game.developer }
    }

    fun getGameById(id: Int): Game? {
        return gameRepository.findGameById(id)
    }

    fun getAllGames(): Iterable<Game> {
        return gameRepository.findAll()
    }

    fun getGamesByGameConsoleId(id: Int): Iterable<Game> {
        val gameConsole =
            gameConsoleRepository.findGameConsoleById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        return gameRepository.findGamesByGameConsolesContains(gameConsole)
    }

    fun getGamesByNameSearch(nameSearch: String): Iterable<Game> {
        return gameRepository.findGamesByNameContainsIgnoreCase(nameSearch)
    }

    fun getGamesByConsoleIdAndNameSearch(consoleId: Int, nameSearch: String): Iterable<Game> {
        val gameConsole = gameConsoleRepository.findGameConsoleById(consoleId) ?: return emptyList()
        return gameRepository.findGamesByGameConsolesContainsAndNameContainsIgnoreCase(gameConsole, nameSearch)
    }
}
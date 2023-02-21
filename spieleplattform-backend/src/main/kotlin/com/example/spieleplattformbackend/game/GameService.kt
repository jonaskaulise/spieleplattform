package com.example.spieleplattformbackend.game

import com.example.spieleplattformbackend.exceptions.BadRequestException
import com.example.spieleplattformbackend.gameConsole.GameConsole
import com.example.spieleplattformbackend.gameConsole.GameConsoleRepository
import com.example.spieleplattformbackend.gameConsole.GameConsoleService
import com.example.spieleplattformbackend.rating.Rating
import com.example.spieleplattformbackend.rating.RatingRepository
import com.example.spieleplattformbackend.user.UserService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class GameService(
    val gameRepository: GameRepository,
    val ratingRepository: RatingRepository,
    val gameConsoleRepository: GameConsoleRepository,
    val userService: UserService,
    val gameConsoleService: GameConsoleService
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
            "author1",
            "Minecraft ist ein Sandbox-Computerspiel, das ursprünglich vom schwedischen Programmierer Markus „Notch“ Persson und seinem dazu gegründeten Unternehmen Mojang entwickelt wurde. Mojang samt Spiel gehört seit September 2014 zu Microsoft. Minecraft erschien erstmals am 17. Mai 2009 als Early-Access-Titel für PC. In der Folge wurde Minecraft für diverse weitere Plattformen und Spielkonsolen veröffentlicht. Die meisten davon erhalten bis heute regelmäßig kostenfreie Aktualisierungen mit neuen Inhalten.",
            "https://www.minecraft.net/content/dam/games/minecraft/key-art/Games_Subnav_Minecraft-300x465.jpg",
            "MmB9b5njVbA"
        )
        minecraft.ratings.add(Rating(5, 4, 5, 4, "Best Game ever", "user1", minecraft))
        minecraft.ratings.add(Rating(4, 3, 4, 3, "Good, but only Blocks", "user2", minecraft))
        minecraft.ratings.add(Rating(2, 1, 2, 1, "Game is ok, but i dont like the graphics", "user3", minecraft))
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
            "author2",
            "Die Sims 4 ist eine Lebenssimulation des Maxis-Entwicklerstudios The Sims Studio. Publisher des Videospiels ist Electronic Arts. Es handelt sich um den regulär vierten Teil der Die-Sims-Reihe und den Nachfolger zu Die Sims 3. Das Videospiel ist in Europa am 4. September 2014 für Windows und am 17. Februar 2015 für OS X erschienen. Der Titel erhielt gemischte Kritiken.",
            "https://simscommunity.info/wp-content/uploads/2019/07/boxart.jpg",
            "GJENRAB4ykA"
        )
        sims4.ratings.add(Rating(5, 4, 3, 2, "I love it", "user3", sims4))
        sims4.gameConsoles = mutableListOf(ps4, xboxOne)
        ps4.games.add(sims4)
        xboxOne.games.add(sims4)

        //GTA 5
        val gta5 = Game(
            "Grand Theft Auto V",
            LocalDate.of(2013, 9, 17),
            "Rockstar North",
            "author3",
            "Grand Theft Auto V (kurz GTA V) ist ein Open-World-Computerspiel, das vom schottischen Studio Rockstar North entwickelt wurde. Die weltweite Veröffentlichung durch Rockstar Games für PlayStation 3 und Xbox 360 fand am 17. September 2013 statt. Es ist der insgesamt fünfzehnte Teil der Videospielserie Grand Theft Auto (GTA) und der fünfte Hauptteil nach Grand Theft Auto IV. GTA V spielt in der fiktiven Stadt Los Santos, die Los Angeles zitiert und parodiert. Los Santos liegt im fiktiven Bundesstaat San Andreas, der Südkalifornien ähnelt und zuvor bereits Schauplatz des fünften Grand-Theft-Auto-Spiels Grand Theft Auto: San Andreas war. Erstmals in der Seriengeschichte steuert der Spieler drei unterschiedliche Protagonisten.",
            "https://media.rockstargames.com/rockstargames/img/global/news/upload/actual_1364906194.jpg",
            "QkkoHAzjnUs"
        )
        gta5.ratings.add(Rating(5, 4, 2, 2, "Awesome game, you can do whatever you want!", "user4", gta5))
        gta5.ratings.add(Rating(3, 4, 5, 1, "Good game, but to much violence", "user1", gta5))
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
            "author1",
            "Super Mario Bros. (japanisch スーパーマリオブラザーズ, Sūpā Mario Burazāzu) ist ein Jump-’n’-Run-Videospiel des japanischen Unternehmens Nintendo. In Japan erschien es am 13. September 1985 für die Heimkonsole Famicom. Bis 1987 kam das Spiel auch im Westen für das Famicom-Pendant Nintendo Entertainment System (NES) auf den Markt.",
            "https://image.konsolenkost.de/item/images/9074377/full/nes-super-mario-bros-1-alternatives-cover.jpg",
            "NTa6Xbzfq1U"
        )
        superMario1.ratings.add(Rating(5, 1, 2, 3, "I love this game", "user3", superMario1))
        superMario1.gameConsoles = mutableListOf(switch, wii)
        switch.games.add(superMario1)
        wii.games.add(superMario1)

        //Tetris
        val tetris = Game(
            "Tetris",
            LocalDate.of(1984, 6, 6),
            "Alexei Paschitnow",
            "author2",
            "Tetris (russisch Тетрис) ist ein puzzleartiges Computerspiel des russischen Programmierers Alexei Paschitnow, der die erste spielbare Version am 6. Juni 1984 auf einem Elektronika-60-Rechner fertigstellte. Tetris gilt heute als Computerspiel-Klassiker, dessen Ableger sich bis heute insgesamt über 425 Millionen Mal verkauft haben, vielfach ausgezeichnet wurden und für mehr als 65 Computerplattformen erschienen.",
            "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/95b13597-76f2-4029-b752-a03e008ee525/d5zi7e0-06488650-7059-49bc-82ee-27bcc5e6cba9.jpg/v1/fill/w_1024,h_1455,q_75,strp/tetris_original_cover_by_zalmay_d5zi7e0-fullview.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7ImhlaWdodCI6Ijw9MTQ1NSIsInBhdGgiOiJcL2ZcLzk1YjEzNTk3LTc2ZjItNDAyOS1iNzUyLWEwM2UwMDhlZTUyNVwvZDV6aTdlMC0wNjQ4ODY1MC03MDU5LTQ5YmMtODJlZS0yN2JjYzVlNmNiYTkuanBnIiwid2lkdGgiOiI8PTEwMjQifV1dLCJhdWQiOlsidXJuOnNlcnZpY2U6aW1hZ2Uub3BlcmF0aW9ucyJdfQ.yja-b5ICUkd1QfY0s6CuM1Tb-qjC8sKJzz_GNEex9Lk",
            "QQ5U-rN7Veg"
        )
        tetris.gameConsoles = mutableListOf(gameBoy)
        gameBoy.games.add(tetris)

        //WiiSports
        val wiiSports = Game(
            "Wii Sports",
            LocalDate.of(2006, 11, 19),
            "Electronic Arts",
            "author3",
            "Wii Sports ist eine Sportsimulation für die Wii-Spielkonsole von Nintendo, die in den USA und Europa oftmals im Lieferumfang der Konsole enthalten war. Die Spiele innerhalb von Wii Sports entstanden ursprünglich aus einzelnen Technikdemos, die angefertigt wurden, um den Fortschritt bei der Entwicklung der Wii-Fernbedienung zu testen. Auf Initiative von Shigeru Miyamoto wurden die einzelnen Spiele in Wii Sports zusammengefasst.",
            "https://www.nintendofans.de/cover/Big_Cover_127788.jpg",
            "zqaPFAZS1K8"
        )
        wiiSports.gameConsoles = mutableListOf(wii)
        wii.games.add(wiiSports)

        //Mario Kart 8
        val marioKart8 = Game(
            "Mario Kart 8 Deluxe",
            LocalDate.of(2014, 5, 24),
            "Nintendo",
            "author1",
            "Mario Kart 8 (japanischer Originaltitel: マリオカート8, Mario Kāto Eito) ist ein Fun-Racer (Rennspiel), der in Europa am 30. Mai 2014 für die Wii U erschienen ist. Es ist der insgesamt elfte Teil der Mario-Kart-Serie, die drei Spiele für Arcade-Automaten miteingerechnet, und der achte Teil der Hauptserie. Am 28. April 2017 erschien mit Mario Kart 8 Deluxe eine erweiterte Version für die Nintendo Switch.",
            "https://s3.gaming-cdn.com/images/products/2615/orig-fallback-v1/mario-kart-8-deluxe-switch-switch-spiel-nintendo-eshop-europe-cover.jpg",
            "tKlRN2YpxRE"
        )
        marioKart8.gameConsoles = mutableListOf(wiiU, switch)
        wiiU.games.add(marioKart8)
        switch.games.add(marioKart8)

        //Overwatch
        val overwatch = Game(
            "Overwatch",
            LocalDate.of(2016, 5, 24),
            "Blizzard Entertainment",
            "author2",
            "Overwatch war ein Multiplayer-Ego-Shooter des US-amerikanischen Spielentwicklers Blizzard Entertainment. Das Spielgeschehen war nach Warcraft, StarCraft und Diablo im insgesamt vierten Phantasieuniversum Blizzards angesiedelt. Overwatch erschien im Mai 2016 für Microsoft Windows, PlayStation 4 und Xbox One. Seither werden in regelmäßigen Abständen Fehlerbehebungen (Patches) und Updates veröffentlicht. Eine Version für Nintendo Switch war seit dem 15. Oktober 2019 erhältlich.",
            "https://www.productcodes.de/wp-content/uploads/2021/01/Overwatch.jpg",
            "dushZybUYnM"
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
            "author3",
            "Red Dead Redemption 2 (kurz RDR2) ist ein Computerspiel aus dem Action-Adventure-Genre. Das Spiel wurde von Rockstar Games entwickelt und spielt in einer offenen Spielwelt. Es erschien im Oktober 2018 für PlayStation 4 und Xbox One und im November 2019 für Microsoft Windows. Seit dem 19. November 2019 ist das Spiel auch für Google Stadia verfügbar. Red Dead Redemption 2 ist nach Red Dead Revolver (2004) und Red Dead Redemption (2010) der dritte Teil der im Wilden Westen angesiedelten Red-Dead-Reihe.",
            "https://image.api.playstation.com/cdn/UP1004/CUSA03041_00/Hpl5MtwQgOVF9vJqlfui6SDB5Jl4oBSq.png",
            "gmA6MrX81z4"
        )
        redDeadRedemption2.gameConsoles = mutableListOf(xboxOne, ps4)
        xboxOne.games.add(redDeadRedemption2)
        ps4.games.add(redDeadRedemption2)

        //Call of Duty: Black Ops
        val codBlackOps = Game(
            "Call of Duty: Black Ops",
            LocalDate.of(2010, 9, 9),
            "Activision",
            "author1",
            "Call of Duty: Black Ops ist ein von Treyarch entwickelter Ego-Shooter, welcher am 9. November 2010 weltweit von Activision für PC (Windows und macOS), Xbox 360, PlayStation 3 und Wii veröffentlicht wurde. Das Spiel stellt den siebten Teil der Call-of-Duty-Reihe und den dritten von Treyarch entwickelten Teil dar. Auf PC, Xbox 360 und PlayStation 3 ist Black Ops in 3D spielbar.",
            "https://static.posters.cz/image/750/poster/call-of-duty-black-ops-cover-i8700.jpg",
            "OPTOVQFRggI"
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

        print("Example data created in database")
    }

    fun printGameWithName(name: String) {
        val game = gameRepository.findGameByName(name) ?: return
        println(game.toString())
    }

    fun getGamesCount(): Long = gameRepository.count()

    fun getFirstGameStartingWithM(): Game? = gameRepository.findFirstByNameStartingWith("M")

    fun findDevelopersStartingWithRorN(): List<String> {
        val games = gameRepository.findAllByDeveloperStartingWithOrDeveloperStartingWith("R", "N")

        return games.map { game: Game -> game.developer }
    }

    fun getGameById(id: Int): Game? {
        return gameRepository.findByIdOrNull(id)
    }

    fun getAllGames(): Iterable<Game> {
        return gameRepository.findAll()
    }

    fun getGamesByGameConsoleId(id: Int): Iterable<Game>? {
        val gameConsole =
            gameConsoleRepository.findByIdOrNull(id) ?: return null
        return gameRepository.findGamesByGameConsolesContains(gameConsole)
    }

    fun getGamesByNameSearch(nameSearch: String): Iterable<Game> {
        return gameRepository.findGamesByNameContainsIgnoreCase(nameSearch)
    }

    fun getGamesByGameConsoleIdAndNameSearch(consoleId: Int, nameSearch: String): Iterable<Game>? {
        val gameConsole =
            gameConsoleRepository.findByIdOrNull(consoleId) ?: return null
        return gameRepository.findGamesByGameConsolesContainsAndNameContainsIgnoreCase(gameConsole, nameSearch)
    }

    fun getGamesByOptionalGameConsoleIdAndNameSearch(gameConsoleId: Int?, nameSearch: String): Iterable<Game>? {
        return when {
            (gameConsoleId == null && nameSearch == "") -> getAllGames()
            (gameConsoleId != null && nameSearch == "") -> getGamesByGameConsoleId(gameConsoleId)
            (gameConsoleId == null) -> getGamesByNameSearch(nameSearch)
            else -> getGamesByGameConsoleIdAndNameSearch(gameConsoleId, nameSearch)
        }
    }

    fun saveGame(addUpdateGameDTO: AddUpdateGameDTO): Game {
        val user = userService.getCurrentUser() ?: throw BadRequestException("User doesn't exist.")
        if (!user.isAuthor()) throw BadRequestException("User is not an author.")
        if (gameRepository.existsByName(addUpdateGameDTO.name)) throw BadRequestException("Game with name exists already.")
        val gameConsoles = gameConsoleService.getGameConsolesByGameConsoleIds(addUpdateGameDTO.gameConsoleIds)
            ?: throw BadRequestException("At least one Console doesn't exist.")

        val game = Game(
            addUpdateGameDTO.name,
            addUpdateGameDTO.releaseDate,
            addUpdateGameDTO.developer,
            user.username,
            addUpdateGameDTO.description,
            addUpdateGameDTO.imageUrl,
            addUpdateGameDTO.youtubeId,
            gameConsoles
        )
        gameRepository.save(game)
        return game
    }

    fun updateGame(id: Int, addUpdateGameDTO: AddUpdateGameDTO): Game {
        val user = userService.getCurrentUser() ?: throw BadRequestException("User doesn't exist.")
        val game = gameRepository.findByIdOrNull(id) ?: throw BadRequestException("Game with given id doesn't exist.")
        if (game.authorUsername != user.username) throw BadRequestException("User is not allowed to update Game.")
        val gameConsoles = gameConsoleService.getGameConsolesByGameConsoleIds(addUpdateGameDTO.gameConsoleIds)
            ?: throw BadRequestException("At least on Console doesn't exist.")

        game.name = addUpdateGameDTO.name
        game.releaseDate = addUpdateGameDTO.releaseDate
        game.developer = addUpdateGameDTO.developer
        game.description = addUpdateGameDTO.description
        game.imageUrl = addUpdateGameDTO.imageUrl
        game.youtubeId = addUpdateGameDTO.youtubeId
        game.gameConsoles = gameConsoles

        gameRepository.save(game)
        return game
    }
}
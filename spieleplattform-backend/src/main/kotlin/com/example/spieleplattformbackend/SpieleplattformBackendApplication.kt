package com.example.spieleplattformbackend

import com.example.spieleplattformbackend.services.GameService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class SpieleplattformBackendApplication {
    @Bean
    fun run(gameService: GameService) : CommandLineRunner {
        return (CommandLineRunner {
            gameService.insertExampleGames()
            println("GameCount: " + gameService.getGamesCount())
            println("Developers: " + gameService.findDevelopersStartingWithRorN())
            val firstGameWithM = gameService.findFirstGameStartingWithM()
            if (firstGameWithM != null)
                println("FirstGameWithM: " + firstGameWithM.name)
            else
                println("NoGameStartingWithM")
            gameService.printGameWithName("Minecraft")
            //println(gameService.findGameWithId(1))
        })
    }
}

fun main(args: Array<String>) {
    runApplication<SpieleplattformBackendApplication>(*args)
}
package com.example.spieleplattformbackend

import com.example.spieleplattformbackend.game.GameService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@SpringBootApplication
class SpieleplattformBackendApplication {
    @Bean
    fun run(gameService: GameService) : CommandLineRunner {
        return (CommandLineRunner {
            gameService.insertExampleData()
            println("GameCount: " + gameService.getGamesCount())
            println("Developers: " + gameService.findDevelopersStartingWithRorN())
            val firstGameWithM = gameService.findFirstGameStartingWithM()
            if (firstGameWithM != null)
                println("FirstGameWithM: " + firstGameWithM.name)
            else
                println("NoGameStartingWithM")
            gameService.printGameWithName("Minecraft")
        })
    }

    @Bean
    fun corsConfigurer() : WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:3000")
            }
        }
    }
}

fun main(args: Array<String>) {
    runApplication<SpieleplattformBackendApplication>(*args)
}
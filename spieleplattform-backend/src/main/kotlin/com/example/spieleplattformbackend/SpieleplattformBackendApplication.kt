package com.example.spieleplattformbackend

import com.example.spieleplattformbackend.game.GameService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean


@SpringBootApplication
class SpieleplattformBackendApplication {
    @Bean
    fun run(gameService: GameService): CommandLineRunner {
        return (CommandLineRunner {
            //gameService.insertExampleData()
        })
    }
}

fun main(args: Array<String>) {
    runApplication<SpieleplattformBackendApplication>(*args)
}
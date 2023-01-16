package com.example.spieleplattformbackend.gameConsole

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GameConsoleRepository : JpaRepository<GameConsole, Int> {
    fun findGameConsoleById(id: Int): GameConsole?
}
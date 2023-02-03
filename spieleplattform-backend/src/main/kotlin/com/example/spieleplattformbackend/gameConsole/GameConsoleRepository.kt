package com.example.spieleplattformbackend.gameConsole

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface GameConsoleRepository : CrudRepository<GameConsole, Int> {}
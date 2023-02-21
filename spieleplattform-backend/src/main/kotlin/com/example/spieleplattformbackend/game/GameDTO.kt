package com.example.spieleplattformbackend.game

import java.time.LocalDate

data class GameDTO(
    var name: String,
    var releaseDate: LocalDate,
    var description: String,
    var imgUrl: String,
    var youtubeId: String,
    var gameConsoleIds: MutableList<Int>
) {}
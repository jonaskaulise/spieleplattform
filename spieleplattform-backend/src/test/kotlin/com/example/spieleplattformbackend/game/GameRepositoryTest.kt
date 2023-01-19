package com.example.spieleplattformbackend.game

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.repository.findByIdOrNull
import java.time.LocalDate

@DataJpaTest
class GameRepositoryTest(
    @Autowired val gameRepository: GameRepository
) {

    @AfterEach
    fun tearDown() {
        gameRepository.deleteAll()
    }

    @Test
    fun itShouldReturnGame() {
        //given
        val game = Game(
            "Minecraft",
            LocalDate.of(2011, 11, 18),
            "Mojang Studios",
            "Minecraft ist ein Sandbox-Computerspiel, das ursprünglich vom schwedischen Programmierer Markus „Notch“ Persson und seinem dazu gegründeten Unternehmen Mojang entwickelt wurde. Mojang samt Spiel gehört seit September 2014 zu Microsoft. Minecraft erschien erstmals am 17. Mai 2009 als Early-Access-Titel für PC. In der Folge wurde Minecraft für diverse weitere Plattformen und Spielkonsolen veröffentlicht. Die meisten davon erhalten bis heute regelmäßig kostenfreie Aktualisierungen mit neuen Inhalten.",
            "https://www.minecraft.net/content/dam/games/minecraft/key-art/Games_Subnav_Minecraft-300x465.jpg",
            "MmB9b5njVbA"
        )
        gameRepository.save(game)

        //when
        val result = gameRepository.findByIdOrNull(1)

        //then
        assertThat(result).isEqualTo(game)
    }
}
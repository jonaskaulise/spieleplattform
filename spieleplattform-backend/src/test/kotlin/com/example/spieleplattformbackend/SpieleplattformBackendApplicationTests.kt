package com.example.spieleplattformbackend

import com.example.spieleplattformbackend.game.Game
import com.example.spieleplattformbackend.game.GameRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.time.LocalDate

@SpringBootTest
@AutoConfigureMockMvc
class SpieleplattformBackendApplicationTests(
    @Autowired val mockMvc: MockMvc,
    @Autowired val gameRepository: GameRepository
) {

    val game = Game(
        "Minecraft",
        LocalDate.of(2011, 11, 18),
        "Mojang Studios",
        "Minecraft ist ein Sandbox-Computerspiel, das ursprünglich vom schwedischen Programmierer Markus „Notch“ Persson und seinem dazu gegründeten Unternehmen Mojang entwickelt wurde. Mojang samt Spiel gehört seit September 2014 zu Microsoft. Minecraft erschien erstmals am 17. Mai 2009 als Early-Access-Titel für PC. In der Folge wurde Minecraft für diverse weitere Plattformen und Spielkonsolen veröffentlicht. Die meisten davon erhalten bis heute regelmäßig kostenfreie Aktualisierungen mit neuen Inhalten.",
        "https://www.minecraft.net/content/dam/games/minecraft/key-art/Games_Subnav_Minecraft-300x465.jpg",
        "MmB9b5njVbA"
    )

    @AfterEach
    fun tearDown() {
        gameRepository.deleteAll()
    }

    @Test
    fun when_gamesOfGameConsoleId_withoutParameters() {
        //given
        gameRepository.save(game)
        //when//then
        mockMvc.get("/games")
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                MockMvcResultMatchers.jsonPath("$[0].name").value("Minecraft")
            }
    }

    @Test
    fun when_game() {
        //given
        gameRepository.save(game)
        //when//then
        mockMvc.get("/games/1")
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                MockMvcResultMatchers.jsonPath("$.name").value("Minecraft")
            }
    }


}

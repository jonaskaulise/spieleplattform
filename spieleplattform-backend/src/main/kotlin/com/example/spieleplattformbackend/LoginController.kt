package com.example.spieleplattformbackend

import com.example.spieleplattformbackend.models.LoginData
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import kotlin.random.Random

@RestController
class LoginController {
    @PostMapping("/login", MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE)
    fun login(@RequestBody loginData: LoginData) : Boolean {
        return Random.nextBoolean()
    }


}
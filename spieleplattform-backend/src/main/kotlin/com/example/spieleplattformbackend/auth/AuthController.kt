package com.example.spieleplattformbackend.auth

import com.example.spieleplattformbackend.auth.dto.LoginDto
import com.example.spieleplattformbackend.auth.dto.LoginResponseDto
import com.example.spieleplattformbackend.auth.dto.RegisterDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(@Autowired var authService: AuthService) {

    @PostMapping("/register")
    fun registration(@RequestBody registerDto: RegisterDto): ResponseEntity<String> {
        return try {
            authService.registration(registerDto)
            ResponseEntity(HttpStatus.OK)
        } catch (exception: Exception) {
            ResponseEntity(exception.message, HttpStatus.BAD_REQUEST)
        }
    }

    @PostMapping("/login")
    fun login(@RequestBody loginDto: LoginDto): LoginResponseDto {
        return authService.login(loginDto)
    }
}
package com.example.spieleplattformbackend.auth

import com.example.spieleplattformbackend.auth.dto.LoginDto
import com.example.spieleplattformbackend.auth.dto.LoginResponseDto
import com.example.spieleplattformbackend.auth.dto.RegisterDto
import com.example.spieleplattformbackend.security.JwtTokenProvider
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    val userRepository: UserRepository,
    val passwordEncoder: PasswordEncoder,
    val authenticationManager: AuthenticationManager,
    val jwtTokenProvider: JwtTokenProvider
) {
    fun registration(registerDto: RegisterDto) {
        if (userRepository.existsByEmail(registerDto.email)) {
            throw Exception("Email-address is already taken")
        }
        val user = UserEntity(
            registerDto.email,
            passwordEncoder.encode(registerDto.password)
        )
        userRepository.save(user)
    }

    fun login(loginDto: LoginDto): LoginResponseDto {
        val authenticationToken = UsernamePasswordAuthenticationToken(loginDto.email, loginDto.password)
        val authentication = authenticationManager.authenticate(authenticationToken)
        SecurityContextHolder.getContext().authentication = authentication
        val jwtToken = jwtTokenProvider.generateToken(authentication.name)

        return LoginResponseDto(jwtToken)
    }
}
package com.example.spieleplattformbackend.user

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UserService(
    val userRepository: UserRepository,
    //val passwordEncoder: PasswordEncoder
) {
    //val passwordEncoder: BCryptPasswordEncoder = BCryptPasswordEncoder()

    fun registration(userData: User) {
        if (userRepository.existsById(userData.username)) {
            throw ResponseStatusException(HttpStatus.CONFLICT)
        }
        val user = User(
            userData.username,
            //passwordEncoder.encode(userData.password)
            userData.password
        )
        userRepository.save(user)
    }
}
package com.example.spieleplattformbackend.security

import com.example.spieleplattformbackend.user.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.getUserByUsername(username) ?: throw UsernameNotFoundException("User not found")

        return User(user.username, user.password, emptyList())
    }
}
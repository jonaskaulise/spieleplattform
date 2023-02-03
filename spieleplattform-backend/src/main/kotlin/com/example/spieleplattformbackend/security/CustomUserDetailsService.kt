package com.example.spieleplattformbackend.security

import com.example.spieleplattformbackend.auth.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(@Autowired val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.getUserByEmail(username) ?: throw UsernameNotFoundException("User not found")

        return User(user.email, user.password, emptyList())
    }
}
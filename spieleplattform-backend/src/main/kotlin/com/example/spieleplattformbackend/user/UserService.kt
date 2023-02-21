package com.example.spieleplattformbackend.user

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Service

@Service
class UserService() {
    fun getCurrentUsername(): String? {
        val token = SecurityContextHolder.getContext().authentication.principal as Jwt
        return token.claims["preferred_username"] as String?
    }

    fun getCurrentUser(): User? {
        val token = SecurityContextHolder.getContext().authentication.principal as Jwt
        val username = token.claims["preferred_username"] as String? ?: return null
        val firstname = token.claims["given_name"] as String? ?: return null
        val lastname = token.claims["family_name"] as String? ?: return null
        val email = token.claims["email"] as String? ?: return null
        val id = token.claims["sid"] as String? ?: return null
        val realmAccess = token.claims["realm_access"] as Map<*, *>? ?: return null
        val roles = (realmAccess["roles"] as List<*>?) ?: return null

        return User(username, firstname, lastname, email, roles, id)
    }
}
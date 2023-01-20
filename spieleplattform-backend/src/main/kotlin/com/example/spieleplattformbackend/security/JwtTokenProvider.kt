package com.example.spieleplattformbackend.security

import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.security.Key
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*

@Component
class JwtTokenProvider {
    val key: Key = Keys.secretKeyFor(SignatureAlgorithm.HS512)

    fun generateToken(userEmail: String): String {
        val now = Instant.now()
        val expiration = now.plus(2, ChronoUnit.HOURS);

        return Jwts.builder()
            .setSubject(userEmail)
            .setIssuedAt(Date.from(now))
            .setExpiration(Date.from(expiration))
            .signWith(key)
            .compact()
    }

    fun getUserMailFromToken(token: String): String {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).body.subject
    }

    fun validateToken(token: String): Boolean {
        return try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
            true
        } catch (e: JwtException) {
            print("Json-web-token not valid!")
            false
        }
    }
}
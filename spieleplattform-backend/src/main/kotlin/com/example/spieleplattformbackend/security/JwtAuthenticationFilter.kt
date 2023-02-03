package com.example.spieleplattformbackend.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter


class JwtAuthenticationFilter() : OncePerRequestFilter() {

    @Autowired
    lateinit var jwtTokenProvider: JwtTokenProvider

    @Autowired
    lateinit var customUserDetailsService: CustomUserDetailsService

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val jwt = getJwtFromRequest(request)

        if (jwt != null && jwtTokenProvider.validateToken(jwt)) {
            val email = jwtTokenProvider.getUserMailFromToken(jwt)
            val userDetails = customUserDetailsService.loadUserByUsername(email)
            val authenticationToken = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
            authenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
            SecurityContextHolder.getContext().authentication = authenticationToken
        }

        filterChain.doFilter(request, response)
    }

    private fun getJwtFromRequest(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7)
        }

        return null
    }

}
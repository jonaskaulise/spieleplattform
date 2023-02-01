package com.example.spieleplattformbackend.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
@EnableWebSecurity
class SecurityConfig(
    @Autowired val unauthorizedHandler: JwtAuthenticationEntryPoint
) {
    private val keycloakLogoutHandler = KeycloakLogoutHandler()

    @Bean
    fun jwtAuthenticationFilter(): JwtAuthenticationFilter {
        return JwtAuthenticationFilter()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    //Double check that:
    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:3000")
            }
        }
    }

//    @Bean
//    fun filterChain(http: HttpSecurity): SecurityFilterChain {
//        http
//            .csrf().disable()
//            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
//            .and()
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and()
//            .authorizeHttpRequests { auth ->
//                auth.requestMatchers("/auth/**").permitAll()
//                auth.requestMatchers("/games/**").permitAll()
//                auth.requestMatchers("/gameConsoles/**").permitAll()
//                auth.anyRequest().authenticated()
//            }
//            .httpBasic()
//
//
//        return http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter::class.java).build()
//    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .cors().and().csrf().disable()
            .authorizeHttpRequests { auth ->
                //auth.requestMatchers("/auth/**").permitAll()
                //auth.requestMatchers("/games/**").permitAll()
                auth.requestMatchers("/gameConsoles/**").permitAll()
                auth.anyRequest().authenticated()
            }

        http.oauth2Login()
            .and()
            .logout()
            .addLogoutHandler(keycloakLogoutHandler)
            .logoutSuccessUrl("/")

        http.oauth2ResourceServer{obj: OAuth2ResourceServerConfigurer<HttpSecurity?> ->obj.jwt()}

        return http.build()
    }
}
package com.example.spieleplattformbackend.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(
    @Autowired val userService: UserService
) {
    @GetMapping("/current")
    fun getCurrentUser(): User? {
        return userService.getCurrentUser()
    }
}
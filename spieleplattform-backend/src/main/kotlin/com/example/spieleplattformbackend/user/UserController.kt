package com.example.spieleplattformbackend.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(@Autowired var userService: UserService) {

    @PostMapping("/registration")
    fun registration(@RequestBody userData: User) {
        userService.registration(userData)
    }
//
//    @PostMapping("/login", MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE)
//    fun login(@RequestBody authData: AuthenticationData) : Boolean {
//        return Random.nextBoolean()
//    }


}
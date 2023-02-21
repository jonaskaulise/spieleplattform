package com.example.spieleplattformbackend.rating

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/ratings")
class RatingController(@Autowired var ratingService: RatingService) {

    @PostMapping
    fun addRating(@RequestBody ratingDTO: RatingDTO): Rating {
        return ratingService.saveRating(ratingDTO)
            ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST)
    }
}
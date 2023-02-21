package com.example.spieleplattformbackend.rating

import com.example.spieleplattformbackend.exceptions.BadRequestException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import javax.naming.AuthenticationException

@RestController
@RequestMapping("/ratings")
class RatingController(@Autowired var ratingService: RatingService) {

    @PostMapping
    fun addRating(@RequestBody addRatingDTO: AddRatingDTO): Rating {
        try {
            return ratingService.saveRating(addRatingDTO)
        } catch (exception: BadRequestException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, exception.message)
        } catch (exception: AuthenticationException) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.message)
        }
    }
}
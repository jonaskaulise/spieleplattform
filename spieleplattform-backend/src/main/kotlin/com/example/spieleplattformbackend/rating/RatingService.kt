package com.example.spieleplattformbackend.rating

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RatingService(@Autowired val ratingRepository: RatingRepository) {

}
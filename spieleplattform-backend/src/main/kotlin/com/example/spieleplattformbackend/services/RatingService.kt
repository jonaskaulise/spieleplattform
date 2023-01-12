package com.example.spieleplattformbackend.services

import com.example.spieleplattformbackend.repositories.RatingRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RatingService(@Autowired val ratingRepository: RatingRepository) {

}
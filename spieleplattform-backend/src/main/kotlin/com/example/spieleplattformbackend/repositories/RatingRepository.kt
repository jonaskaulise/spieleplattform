package com.example.spieleplattformbackend.repositories

import com.example.spieleplattformbackend.models.Rating
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

// blub
@Repository
interface RatingRepository : CrudRepository<Rating, Int> {
}
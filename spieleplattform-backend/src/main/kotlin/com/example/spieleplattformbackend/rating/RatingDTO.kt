package com.example.spieleplattformbackend.rating

data class RatingDTO(
    var graphicRating: Int,
    var soundRating: Int,
    var addictionRating: Int,
    var actionRating: Int,

    var comment: String,
    var gameId: Int
) {
}
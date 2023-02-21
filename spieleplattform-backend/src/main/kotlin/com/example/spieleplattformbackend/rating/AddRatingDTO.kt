package com.example.spieleplattformbackend.rating

data class AddRatingDTO(
    var graphicRating: Int,
    var soundRating: Int,
    var addictionRating: Int,
    var actionRating: Int,

    var comment: String,
    var gameId: Int
) {
    fun hasValidRatingValues(): Boolean {
        return graphicRating in 1..5
                && soundRating in 1..5
                && addictionRating in 1..5
                && actionRating in 1..5
    }
}
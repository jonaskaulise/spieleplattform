package com.example.spieleplattformbackend.rating

import org.junit.jupiter.api.Test

class AddRatingDTOTest {

    @Test
    fun `hasValidRatingValues returns true with valid values`() {
        //given
        val addRatingDTO = AddRatingDTO(1, 2, 3, 4, "test", 1)

        //when
        val result = addRatingDTO.hasValidRatingValues()

        //then
        assert(result)
    }

    @Test
    fun `hasValidRatingValues returns false with invalid values`() {
        //given
        val addRatingDTO = AddRatingDTO(-1, 2, 8, 0, "test", 1)

        //when
        val result = addRatingDTO.hasValidRatingValues()

        //then
        assert(!result)
    }
}
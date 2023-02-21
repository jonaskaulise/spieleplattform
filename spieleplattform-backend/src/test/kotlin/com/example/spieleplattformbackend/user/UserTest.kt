package com.example.spieleplattformbackend.user

import org.junit.jupiter.api.Test

class UserTest {
    @Test
    fun `isAuthor returns true if user contains author-role`() {
        //given
        val user = User("user1", "user", "1", "user@1.de", mutableListOf("author", "user"), "1")

        //when
        val result = user.isAuthor()

        //then
        assert(result)
    }

    @Test
    fun `isAuthor returns false if user doesn't contain author-role`() {
        //given
        val user = User("user1", "user", "1", "user@1.de", mutableListOf("user"), "1")

        //when
        val result = user.isAuthor()

        //then
        assert(!result)
    }
}
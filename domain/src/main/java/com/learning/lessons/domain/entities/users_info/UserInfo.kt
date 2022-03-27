package com.learning.lessons.domain.entities.users_info

data class UserInfo(
    val questionsIDs: List<Int> = listOf(),
    val likesLessons: List<Int> = listOf(),
    val likesWebinars: List<Int> = listOf(),
    val dislikesWebinars: List<Int> = listOf(),
    val user_id: Int = 0,
    val dislikesLessons: List<Int> = listOf(),
    val passedLessons: List<Int> = listOf()
)

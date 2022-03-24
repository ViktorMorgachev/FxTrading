package com.fx_trading.lessons.domain.entities.users_info

data class UserInfo(
    val questionsIDs: List<Long> = listOf(),
    val likesLessons: List<Long> = listOf(),
    val user_id: Long = 0,
    val dislikesLessons: List<Long> = listOf(),
    val passedLessons: List<Long> = listOf()
)

package com.learning.lessons.domain.entities.users_info

data class UserInfo(
    val questionsIDs: List<Int>,
    val likesLessons: List<Int>,
    val likesWebinars: List<Int>,
    val dislikesWebinars: List<Int>,
    val user_id: Int = 0,
    val dislikesLessons: List<Int>,
    val passedLessons: List<Int>,
    val devices_ids: List<String>,
)

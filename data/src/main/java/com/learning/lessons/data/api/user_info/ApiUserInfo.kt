package com.learning.lessons.data.api.user_info


data class ApiUserInfo(
    val passed_questions_ids: List<Int> = listOf(),
    val likes_lessons_ids: List<Int> = listOf(),
    val likes_webinars_ids: List<Int> = listOf(),
    val dislikes_webinars_ids: List<Int> = listOf(),
    val user_id: Int = 0,
    val dislikes_lessons_ids: List<Int> = listOf(),
    val passed_lessons_ids: List<Int> = listOf(),
    val devices_ids: List<String> = listOf(),
)

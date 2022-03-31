package com.learning.lessons.data.api.lesson

import com.learning.lessons.data.api.common.ApiTimeCode

data class ApiLesson(
    val categories: List<String> = listOf(),
    val comments: List<ApiComment> = listOf(),
    val description: String = "",
    val difficulty: Int = 0,
    val difficultyID: Int = 0,
    val dislikes: Int = 0,
    val duration: String = "",
    val id: Int = 0,
    val active: Boolean = false,
    val language: String = "",
    val likes: Int = 0,
    val promo_image_url: String = "",
    val questions_group: Int = 0,
    val region: String = "",
    val sort_order: Int = -1,
    val speaker_name: String = "",
    val tags: List<String> = listOf(),
    val text_version_link: String = "",
    val timeCodes: List<ApiTimeCode> = listOf(),
    val title: String = "",
    val video_url: String = ""
)
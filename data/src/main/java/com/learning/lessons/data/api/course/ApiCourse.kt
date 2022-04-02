package com.learning.lessons.data.api.course

data class ApiCourse(
    val description: String = "",
    val dislikes: Int = 0,
    val id: Int = 0,
    val active: Boolean = false,
    val language: String = "",
    val lessons_ids: List<Int> = listOf(),
    val likes: Int = 0,
    val difficulty: Int = 0,
    val title: String = "",
    val promo_image_url: String ="",
    val region: String = ""
)
package com.learning.lessons.data.api.course

data class ApiCourse(
    val description: String,
    val dislikes: Int,
    val id: Int,
    val active: Boolean,
    val language: String,
    val lessons_ids: List<Int>,
    val likes: Int,
    val difficulty: Int,
    val title: String,
    val promo_image_url: String,
    val region: String
)
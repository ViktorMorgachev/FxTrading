package com.fx_trading.lessons.domain.entities.lesson

import com.fx_trading.lessons.domain.entities.common.Timecode

data class Lesson(
    val categories: List<String>,
    val comments: List<Comment>,
    val description: String,
    val difficulty: Int,
    val dislikes: Int,
    val duration: String,
    val id: Int,
    val is_active: Boolean,
    val language: String,
    val likes: Int,
    val promo_image_url: String,
    val question_group: Long,
    val region: String,
    val sort_order: Int,
    val speaker_name: String,
    val tags: List<String>,
    val text_version_link: String,
    val timecodes: List<Timecode>,
    val title: String,
    val video_url: String
)

fun Lesson.hasCategory(category: String): Boolean{
    return categories.contains(category)
}
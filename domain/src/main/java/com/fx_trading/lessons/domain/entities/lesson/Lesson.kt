package com.fx_trading.lessons.domain.entities.lesson

import com.fx_trading.lessons.domain.entities.common.Timecode

data class Lesson(
    val categories: List<Category>,
    val comments: List<Comment>,
    val description: String,
    val difficulty: String,
    val dislikes: Int,
    val duration: String,
    val id: Int,
    val is_active: Boolean,
    val language: String,
    val likes: Int,
    val marketing_title: String,
    val promo_image_url: String,
    val quiz_group: List<QuizGroup>,
    val region: String,
    val sort_order: Int,
    val speaker_name: String,
    val tags: List<String>,
    val text_version_link: String,
    val timecodes: List<Timecode>,
    val title: String,
    val type: String,
    val video_url: String
)
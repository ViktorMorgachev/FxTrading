package com.fx_trading.lessons.data.api.lesson

import com.fx_trading.lessons.data.api.common.ApiTimeCode

data class ApiLesson(
    val categories: List<ApiCategory>,
    val apiComments: List<ApiComment>,
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
    val quiz_group: List<ApiQuizGroup>,
    val region: String,
    val sort_order: Int,
    val speaker_name: String,
    val tags: List<String>,
    val text_version_link: String,
    val timeCodes: List<ApiTimeCode>,
    val title: String,
    val type: String,
    val video_url: String
)
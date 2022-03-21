package com.fx_trading.lessons.data.api.lesson

import com.fx_trading.lessons.data.api.common.ApiTimeCode

data class ApiLesson(
    val categories: List<String> = listOf(),
    val comments: List<ApiComment> = listOf(),
    val description: String = "",
    val difficulty: String = "",
    val dislikes: Int = 0,
    val duration: String = "",
    val id: Int = 0,
    val active: Boolean = false,
    val language: String = "",
    val likes: Int = 0,
    val promo_image_url: String = "",
    val question_groups: List<ApiQuestionGroupID> = listOf(),
    val region: String = "",
    val sort_order: Int = -1,
    val speaker_name: String = "",
    val tags: List<String> = listOf(),
    val text_version_link: String = "",
    val timeCodes: List<ApiTimeCode> = listOf(),
    val title: String = "",
    val video_url: String = ""
)
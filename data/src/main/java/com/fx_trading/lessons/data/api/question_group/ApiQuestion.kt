package com.fx_trading.lessons.data.api.question_group

data class ApiQuestion(
    val apiAnswers: List<ApiAnswer>,
    val description: String,
    val difficulty: Int,
    val is_active: Boolean,
    val optional_image_url: String,
    val title: String
)
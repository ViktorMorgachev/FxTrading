package com.fx_trading.lessons.data.api.question_group

data class ApiAnswer(
    val is_active: Boolean,
    val is_correct: Int,
    val optional_image_url: String,
    val text: String
)
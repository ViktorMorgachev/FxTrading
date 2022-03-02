package com.fx_trading.lessons.data.api.quiz

data class Answer(
    val is_active: Boolean,
    val is_correct: Int,
    val optional_image_url: String,
    val text: String
)
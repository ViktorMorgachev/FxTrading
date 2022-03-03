package com.fx_trading.lessons.data.api.quiz

data class ApiQuiz(
    val answers: List<ApiAnswer>,
    val description: String,
    val difficulty: String,
    val is_active: Boolean,
    val optional_image_url: String,
    val title: String
)
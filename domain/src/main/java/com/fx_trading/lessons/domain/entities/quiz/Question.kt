package com.fx_trading.lessons.domain.entities.quiz

data class Question(
    val answers: List<Answer>,
    val description: String,
    val difficulty: String,
    val is_active: Boolean,
    val optional_image_url: String,
    val title: String
)
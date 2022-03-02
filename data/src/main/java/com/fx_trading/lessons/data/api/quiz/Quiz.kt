package com.fx_trading.lessons.data.api.quiz

import com.fx_trading.lessons.data.api.quiz.Answer

data class Quiz(
    val answers: List<Answer>,
    val description: String,
    val difficulty: String,
    val is_active: Boolean,
    val optional_image_url: String,
    val title: String
)
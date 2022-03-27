package com.learning.lessons.domain.entities.quiz

data class Answer(
    val is_active: Boolean,
    val is_correct: Boolean,
    val optional_image_url: String,
    val text: String
)
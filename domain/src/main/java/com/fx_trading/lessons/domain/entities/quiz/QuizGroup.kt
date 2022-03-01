package com.fx_trading.lessons.domain.entities.quiz

import com.fx_trading.lessons.domain.entities.quiz.Quiz

data class QuizGroup(
    val available_attempts: Int,
    val correct_for_success: Int,
    val id: Int,
    val is_active: Boolean,
    val language: String,
    val name: String,
    val parent_id: Int,
    val quizzes: List<Quiz>,
    val region: String
)
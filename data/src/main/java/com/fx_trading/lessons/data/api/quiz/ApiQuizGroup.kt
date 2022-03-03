package com.fx_trading.lessons.data.api.quiz

data class ApiQuizGroup(
    val available_attempts: Int,
    val correct_for_success: Int,
    val id: Int,
    val is_active: Boolean,
    val language: String,
    val name: String,
    val parent_id: Int,
    val quizzes: List<ApiQuiz>,
    val region: String
)
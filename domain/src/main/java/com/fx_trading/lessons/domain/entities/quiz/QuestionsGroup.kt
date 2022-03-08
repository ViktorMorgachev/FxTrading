package com.fx_trading.lessons.domain.entities.quiz

data class QuestionsGroup(
    val available_attempts: Int,
    val correct_for_success: Int,
    val id: Int,
    val is_active: Boolean,
    val language: String,
    val name: String,
    val parent_id: Int,
    val questions: List<Question>,
    val region: String,
    val isStartExam: Boolean
)
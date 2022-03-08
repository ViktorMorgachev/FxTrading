package com.fx_trading.lessons.data.api.question_group

data class ApiQuestionGroup(
    val available_attempts: Int,
    val correct_for_success: Int,
    val id: Int,
    val is_active: Boolean,
    val language: String,
    val name: String,
    val parent_id: Int,
    val apiQuestions: List<ApiQuestion>,
    val region: String,
    val isStartExam: Boolean
)
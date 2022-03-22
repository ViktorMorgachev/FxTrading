package com.fx_trading.lessons.data.api.question_group

data class ApiQuestionGroup(
    val available_attempts: Long,
    val correct_for_success: Long,
    val id: Long,
    val is_active: Boolean,
    val language: String,
    val name: String,
    val parent_id: Long,
    val apiQuestions: List<ApiQuestion>,
    val region: String,
    val is_start_exam: Boolean
)
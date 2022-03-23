package com.fx_trading.lessons.data.api.question_group

data class ApiQuestionGroup(
    val available_attempts: Long = 0,
    val correct_for_success: Long = 0,
    val id: Long = 0,
    val active: Boolean = false,
    val language: String = "",
    val name: String = "",
    val parent_id: Long = 0,
    val questions: List<ApiQuestion> = listOf(),
    val region: String = "",
    val start_exam: Boolean = false
)
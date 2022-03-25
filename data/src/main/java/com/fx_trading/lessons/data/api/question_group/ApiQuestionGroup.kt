package com.fx_trading.lessons.data.api.question_group

data class ApiQuestionGroup(
    val available_attempts: Int = 0,
    val correct_for_success: Int = 0,
    val id: Int = 0,
    val active: Boolean = false,
    val language: String = "",
    val name: String = "",
    val parent_id: Int = 0,
    val questions: List<ApiQuestion> = listOf(),
    val region: String = "",
    val start_exam: Boolean = false
)
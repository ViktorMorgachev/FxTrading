package com.fx_trading.lessons.data.api.question_group

data class ApiQuestion(
    val answers: List<ApiAnswer> = listOf(),
    val description: String = "",
    val difficulty: Long = 0,
    val active: Boolean = false,
    val optional_image_url: String = "",
    val title: String = ""
)
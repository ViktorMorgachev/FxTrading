package com.learning.lessons.data.api.question_group

data class ApiAnswer(
    val active: Boolean = false,
    val correct: Int = 0,
    val optional_image_url: String = "",
    val text: String = ""
)
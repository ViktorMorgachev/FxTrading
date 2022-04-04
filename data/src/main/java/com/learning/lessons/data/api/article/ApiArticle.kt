package com.learning.lessons.data.api.article

data class ApiArticle(
    val active: Boolean = false,
    val id: Int= 0,
    val title: String = "",
    val date_description: String = "",
    val image_url: String = "",
    val difficulty: Int = 0,
    val url: String = "",
    val question_id: Int  = 0,
    val tags: List<String> = listOf()
)

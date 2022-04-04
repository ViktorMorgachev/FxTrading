package com.learning.lessons.domain.entities.article

data class Article(
    val id: Int,
    val url: String,
    val question_id: Int,
    val tags: List<String>
)

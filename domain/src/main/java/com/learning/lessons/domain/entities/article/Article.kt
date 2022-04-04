package com.learning.lessons.domain.entities.article

data class Article(
    val id: Int,
    val title: String,
    val difficulty: Int,
    val dateDescription: String,
    val url: String,
    val imageUrl: String,
    val questionID: Int,
    val tags: List<String>
)

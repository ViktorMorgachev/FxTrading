package com.learning.lessons.domain.repositories

import com.learning.lessons.domain.entities.article.Article

interface ArticleRepository {
    suspend fun getArticles(): List<Article>
    suspend fun getArticleByID(articleID: Int): Article?
}
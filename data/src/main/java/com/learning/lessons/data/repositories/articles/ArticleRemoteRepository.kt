package com.learning.lessons.data.repositories.articles

import com.learning.lessons.data.api.article.ApiArticle

interface ArticleRemoteRepository {
    suspend fun getRemoteArticles(): List<ApiArticle>
    suspend fun getRemoteArticleByID(articleID: Int): ApiArticle?
}
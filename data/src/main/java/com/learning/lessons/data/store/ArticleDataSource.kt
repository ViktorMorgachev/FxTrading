package com.learning.lessons.data.store

import com.learning.lessons.data.mappers.toArticle
import com.learning.lessons.data.repositories.articles.ArticleRemoteRepository
import com.learning.lessons.domain.entities.article.Article
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ArticleDataSource @Inject constructor(private val articleRemoteRepository: ArticleRemoteRepository) {
    suspend fun getArticles(): List<Article> {
        return  articleRemoteRepository.getRemoteArticles().map { it.toArticle() }
    }
    suspend fun getArticleByID(articleID: Int): Article? {
        return  articleRemoteRepository.getRemoteArticleByID(articleID)?.toArticle()
    }

}


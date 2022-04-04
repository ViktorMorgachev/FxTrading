package com.learning.lessons.data.repositories.articles

import com.learning.lessons.data.store.ArticleDataSource
import com.learning.lessons.domain.entities.article.Article
import com.learning.lessons.domain.repositories.ArticleRepository
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class ArticleProvider @Inject constructor(private val articleDataSource: ArticleDataSource): ArticleRepository {

    override suspend fun getArticles(): List<Article> {
       return articleDataSource.getArticles()
    }

    override suspend fun getArticleByID(articleID: Int): Article? {
        return articleDataSource.getArticleByID(articleID)
    }
}
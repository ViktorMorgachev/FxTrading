package com.learning.lessons.domain.usecases

import com.learning.lessons.domain.entities.article.Article
import com.learning.lessons.domain.repositories.ArticleRepository
import com.learning.lessons.domain.repositories.UserInfoRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleUseCase @Inject constructor(
    private val articleRepository: ArticleRepository,
    private val userInfoRepository: UserInfoRepository
) {

    suspend fun getArticles(): List<Article> {
        return articleRepository.getArticles()
    }

    suspend fun getArticleByID(articleID: Int): Article? {
        return articleRepository.getArticleByID(articleID)
    }

    suspend fun likeArticle(articleID: Int): Boolean {
        return false
    }

}
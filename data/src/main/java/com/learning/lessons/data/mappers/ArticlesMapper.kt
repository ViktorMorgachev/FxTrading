package com.learning.lessons.data.mappers

import com.learning.lessons.data.api.article.ApiArticle
import com.learning.lessons.domain.entities.article.Article

fun ApiArticle.toArticle(): Article{
    return Article(id, url, question_id, tags)
}
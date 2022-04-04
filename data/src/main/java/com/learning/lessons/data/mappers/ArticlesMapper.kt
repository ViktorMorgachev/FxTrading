package com.learning.lessons.data.mappers

import com.learning.lessons.data.api.article.ApiArticle
import com.learning.lessons.domain.entities.article.Article

fun ApiArticle.toArticle(): Article{
    return Article(id = id, title = title, difficulty = difficulty, dateDescription = date_description, url = url, questionID = question_id, tags = tags, imageUrl = image_url)
}
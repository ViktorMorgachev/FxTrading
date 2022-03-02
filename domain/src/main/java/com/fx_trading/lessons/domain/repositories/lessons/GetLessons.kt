package com.fx_trading.lessons.domain.repositories.lessons

import com.fx_trading.lessons.domain.entities.lesson.Lesson

interface GetLessons {
    suspend fun getLessons(author: String): List<Lesson>
}
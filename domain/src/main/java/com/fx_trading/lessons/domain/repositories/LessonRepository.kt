package com.fx_trading.lessons.domain.repositories

import com.fx_trading.lessons.domain.entities.lesson.Lesson

interface LessonRepository {
    suspend fun getLessons(): List<Lesson>
}
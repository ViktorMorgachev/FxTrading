package com.fx_trading.lessons.domain.repositories.lessons

import com.fx_trading.lessons.domain.entities.lesson.Lesson

interface LessonRepository {
    suspend fun getLessons(author: String? = null): List<Lesson>
    suspend fun getLessonByID(id: Int): Lesson
}
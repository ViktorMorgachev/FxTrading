package com.fx_trading.lessons.domain.usecases

import com.fx_trading.lessons.domain.entities.lesson.Lesson

interface GetLessonsUseCase {
    fun fetchLessons(): List<Lesson>
}
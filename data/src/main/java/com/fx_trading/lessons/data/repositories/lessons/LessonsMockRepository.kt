package com.fx_trading.lessons.data.repositories.lessons

import com.fx_trading.lessons.data.api.lesson.ApiLesson

interface LessonsMockRepository {
  suspend fun getMockLessons(): List<ApiLesson>
}
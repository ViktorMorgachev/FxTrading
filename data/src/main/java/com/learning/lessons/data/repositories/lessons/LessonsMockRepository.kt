package com.learning.lessons.data.repositories.lessons

import com.learning.lessons.data.api.lesson.ApiLesson

interface LessonsMockRepository {
  suspend fun getMockLessons(): List<ApiLesson>
}
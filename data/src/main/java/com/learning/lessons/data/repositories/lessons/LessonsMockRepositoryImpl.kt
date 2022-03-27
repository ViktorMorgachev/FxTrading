package com.learning.lessons.data.repositories.lessons

import com.learning.lessons.data.api.lesson.ApiLesson
import com.learning.lessons.data.api.lesson.mock.MockData
import kotlinx.coroutines.delay
import javax.inject.Inject

class LessonsMockRepositoryImpl @Inject constructor(): LessonsMockRepository {
    private val lessons = MockData.mockApiLessons
    override suspend fun getMockLessons(): List<ApiLesson> {
        delay(500)
        return lessons
    }
}
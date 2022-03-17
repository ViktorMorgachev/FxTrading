package com.fx_trading.lessons.data.store

import com.fx_trading.lessons.data.BuildConfig
import com.fx_trading.lessons.data.api.lesson.ApiLesson
import com.fx_trading.lessons.data.repositories.lessons.LessonsMockRepository
import com.fx_trading.lessons.data.repositories.lessons.LessonsRemoteRepository
import javax.inject.Inject
import javax.inject.Singleton

val useMockData = BuildConfig.USE_MOCK_DATA


@Singleton
class LessonsDataSource @Inject constructor(
    private val lessonsRemoteRepository: LessonsRemoteRepository,
    private val lessonsMockRepository: LessonsMockRepository
) {
    private var cache: List<ApiLesson> = listOf()

    suspend fun getLessons(): List<ApiLesson> {
        val lessons = when {
            useMockData -> lessonsMockRepository.getMockLessons()
            cache.isNotEmpty() -> return cache
            else -> return lessonsRemoteRepository.getRemoteLessons()
        }
        cache = lessons
        return lessons
    }
}
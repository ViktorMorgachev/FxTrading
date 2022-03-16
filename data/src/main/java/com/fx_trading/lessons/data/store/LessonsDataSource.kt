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
    suspend fun getLessons(): List<ApiLesson> {
        return if (useMockData) lessonsMockRepository.getMockLessons()
        else lessonsRemoteRepository.getRemoteLessons()
    }
}
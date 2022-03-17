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
    companion object{
        var cache: MutableList<ApiLesson> = mutableListOf()
    }

    suspend fun getLessons(): List<ApiLesson> {
        if (cache.isNotEmpty()) return  cache
        val lessons = when {
            useMockData -> lessonsMockRepository.getMockLessons()
            else ->  lessonsRemoteRepository.getRemoteLessons()
        }
        cache = lessons.toMutableList()
        return lessons
    }

    suspend fun setLikeoLesson(lessonID: Long): Boolean{
        val lessonForUpdate = cache.firstOrNull() { it.id == lessonID.toInt() }
        if (lessonForUpdate != null){
            return lessonsRemoteRepository.updateLesson(lessonForUpdate.copy(likes = lessonForUpdate.likes + 1))
        } else return false
    }
}
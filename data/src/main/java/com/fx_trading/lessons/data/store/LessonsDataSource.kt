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

    suspend fun setLikesLesson(lessonID: Long): Boolean{
        val lessonForUpdate = cache.firstOrNull() { it.id == lessonID.toInt() }
        if (lessonForUpdate != null){
            val success =  lessonsRemoteRepository.updateLesson(lessonForUpdate.copy(likes = lessonForUpdate.likes + 1))
            if (success){
                updateCache(lessonForUpdate, lessonForUpdate.copy(likes = lessonForUpdate.likes + 1))
            }
            return true
        } else return false
    }

    private fun updateCache(lessonOld: ApiLesson, lessonNew: ApiLesson){
        cache.remove(lessonOld)
        cache.add(lessonNew)
    }

    suspend fun getLessonByID(lessonID: Long): ApiLesson? {
        if (cache.map { it.id }.contains(lessonID.toInt())){
            return cache.first { it.id == lessonID.toInt() }
        } else {
          return  lessonsRemoteRepository.getRemoteLessonByID(lessonID)
        }
    }
}
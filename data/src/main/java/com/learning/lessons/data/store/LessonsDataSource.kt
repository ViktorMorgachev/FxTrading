package com.learning.lessons.data.store

import com.learning.lessons.data.BuildConfig
import com.learning.lessons.data.api.lesson.ApiLesson
import com.learning.lessons.data.repositories.lessons.LessonsMockRepository
import com.learning.lessons.data.repositories.lessons.LessonsRemoteRepository
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

    suspend fun setLikesLesson(lessonID: Int): Boolean{
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

    suspend fun getLessonByID(lessonID: Int): ApiLesson? {
        if (cache.map { it.id }.contains(lessonID.toInt())){
            return cache.first { it.id == lessonID.toInt() }
        } else {
          return  lessonsRemoteRepository.getRemoteLessonByID(lessonID)
        }
    }

   suspend fun setDislikeLesson(lessonID: Int): Boolean {
       val lessonForUpdate = cache.firstOrNull() { it.id == lessonID.toInt() }
       if (lessonForUpdate != null){
           val success =  lessonsRemoteRepository.updateLesson(lessonForUpdate.copy(dislikes = lessonForUpdate.dislikes + 1))
           if (success){
               updateCache(lessonForUpdate, lessonForUpdate.copy(dislikes = lessonForUpdate.dislikes + 1))
           }
           return true
       } else return false
    }
}
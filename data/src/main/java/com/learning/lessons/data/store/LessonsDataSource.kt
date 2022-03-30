package com.learning.lessons.data.store

import com.learning.lessons.data.mappers.toLesson
import com.learning.lessons.data.repositories.lessons.LessonsRemoteRepository
import com.learning.lessons.domain.entities.lesson.Lesson
import com.learning.lessons.domain.repositories.LessonRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LessonsDataSource @Inject constructor(
    private val lessonsRemoteRepository: LessonsRemoteRepository
) {

     suspend fun getLessons(): List<Lesson> {
        return lessonsRemoteRepository.getRemoteLessons().map { it.toLesson() }
    }

     suspend fun getLessonByID(lessonID: Int): Lesson? {
       return lessonsRemoteRepository.getRemoteLessonByID(lessonID)?.toLesson()
    }

     suspend fun getLessonsByTags(tags: List<String>): List<Lesson> {
      return lessonsRemoteRepository.getRemoteLessons().map { it.toLesson() }
    }

     suspend fun updateLessonField(
        lessonID: Int,
        fieldValue: Any,
        field: String
    ): Deferred<Boolean> = withContext(Dispatchers.IO){
        return@withContext async { lessonsRemoteRepository.updateLessonField(lessonID, fieldValue, field).first() }
    }
    
}
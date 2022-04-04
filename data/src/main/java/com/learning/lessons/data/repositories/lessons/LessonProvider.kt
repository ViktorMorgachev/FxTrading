package com.learning.lessons.data.repositories.lessons

import com.learning.lessons.data.store.LessonsDataSource
import com.learning.lessons.domain.entities.lesson.Lesson
import com.learning.lessons.domain.repositories.LessonRepository
import kotlinx.coroutines.Deferred
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LessonProvider @Inject constructor(private val lessonsDataSource: LessonsDataSource): LessonRepository {

     override suspend fun getLessons(): List<Lesson> {
       return lessonsDataSource.getLessons()
    }

     override suspend fun getLessonByID(lessonID: Int): Lesson? {
      return  lessonsDataSource.getLessonByID(lessonID)
    }

     override suspend fun getLessonsByTags(tags: List<String>): List<Lesson> {
        return lessonsDataSource.getLessonsByTags(tags)
    }

    override suspend fun getLessonsByIDS(lessonsIDS: List<Int>): List<Lesson> {
       return lessonsDataSource.getLessonsByIDS(lessonsIDS)
    }

    override suspend fun updateFields(
        objectID: Int,
        fieldValue: List<Pair<String, Any>>
    ): Deferred<Boolean> {
        return lessonsDataSource.updateLessonFields(objectID, fieldValue)
    }
}
package com.learning.lessons.data.store

import com.learning.lessons.data.mappers.toLesson
import com.learning.lessons.data.repositories.lessons.LessonsRemoteRepository
import com.learning.lessons.domain.entities.lesson.Lesson
import com.learning.lessons.domain.repositories.LessonRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LessonsDataSource @Inject constructor(
    private val lessonsRemoteRepository: LessonsRemoteRepository
): LessonRepository {

    override suspend fun getLessons(): List<Lesson> {
        return lessonsRemoteRepository.getRemoteLessons().map { it.toLesson() }
    }

    override suspend fun getLessonByID(lessonID: Int): Lesson? {
       return lessonsRemoteRepository.getRemoteLessonByID(lessonID)?.toLesson()
    }

    override suspend fun getLessonsByTags(tags: List<String>): List<Lesson> {
      return lessonsRemoteRepository.getRemoteLessons().map { it.toLesson() }
    }

    override suspend fun updateLessonField(lesson: Int, fieldValue: Any, field: String): Boolean {
       return lessonsRemoteRepository.updateLessonField(lesson, fieldValue, field)
    }


}
package com.fx_trading.lessons.data.repositories.lessons

import com.fx_trading.lessons.data.mappers.toLesson
import com.fx_trading.lessons.data.store.LessonsDataSource
import com.fx_trading.lessons.domain.entities.lesson.Lesson
import com.fx_trading.lessons.domain.repositories.LessonRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LessonProvider @Inject constructor(private val lessonsDataSource: LessonsDataSource): LessonRepository {

    override suspend fun getLessons(): List<Lesson> {
         return lessonsDataSource.getLessons().map { it.toLesson() }
    }

    override suspend fun setLikeToLesson(lessonID: Long): Boolean {
        return lessonsDataSource.setLikesLesson(lessonID)
    }

    override suspend fun getLessonByID(lessonID: Long): Lesson? {
      return  lessonsDataSource.getLessonByID(lessonID)?.toLesson()
    }
}
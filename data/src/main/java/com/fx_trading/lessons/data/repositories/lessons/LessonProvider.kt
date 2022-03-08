package com.fx_trading.lessons.data.repositories.lessons

import com.fx_trading.lessons.data.mappers.toLesson
import com.fx_trading.lessons.data.store.LessonsDataSource
import com.fx_trading.lessons.domain.entities.lesson.Lesson
import com.fx_trading.lessons.domain.repositories.LessonRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LessonProvider @Inject constructor(private val lessonsDataSource: LessonsDataSource): LessonRepository {

     override suspend fun getLessons(author: String?): List<Lesson> {
         return lessonsDataSource.getLessons().map { it.toLesson() }
    }

     override suspend fun getLessonByID(id: Int): Lesson {
         return lessonsDataSource.getLessonsByID(id).toLesson()
    }
}
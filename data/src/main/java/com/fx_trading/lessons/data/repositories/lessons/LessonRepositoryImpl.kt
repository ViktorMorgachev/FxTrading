package com.fx_trading.lessons.data.repositories.lessons

import com.fx_trading.lessons.data.mappers.toLesson
import com.fx_trading.lessons.domain.entities.lesson.Lesson
import com.fx_trading.lessons.domain.repositories.lessons.LessonRepository

class LessonRepositoryImpl(private val lessonsRemoteRepository: LessonsRemoteRepository): LessonRepository {

    override suspend fun getLessons(author: String?): List<Lesson> {
        return lessonsRemoteRepository.getRemoteLessons().map { it.toLesson() }
    }

    override suspend fun getLessonByID(id: Int): Lesson {
        return lessonsRemoteRepository.getRemoteLessonByID().toLesson()
    }
}
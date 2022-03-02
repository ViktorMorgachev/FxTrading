package com.fx_trading.lessons.data.repositories.lessons

import com.fx_trading.lessons.data.mappers.toLesson
import com.fx_trading.lessons.domain.entities.lesson.Lesson
import com.fx_trading.lessons.domain.repositories.lessons.GetLessons

class GetLessonsImpl(private val getLessonsRemote: GetLessonsRemote, private val getLessonsLocal: GetLessonsLocal): GetLessons {
    override suspend fun getLessons(author: String): List<Lesson> {
        return getLessonsRemote.getRemoteLessons().map { it.toLesson() }
    }
}
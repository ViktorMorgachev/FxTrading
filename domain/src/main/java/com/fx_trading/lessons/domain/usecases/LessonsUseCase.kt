package com.fx_trading.lessons.domain.usecases

import com.fx_trading.lessons.domain.entities.lesson.Lesson
import com.fx_trading.lessons.domain.repositories.LessonRepository
import javax.inject.Inject


class LessonsUseCase @Inject constructor(private val lessonRepository: LessonRepository){
    suspend fun getLessonById(lessonID: Int): Lesson{
        return lessonRepository.getLessonByID(lessonID)
    }
   suspend fun getLessons(): List<Lesson>{
        return lessonRepository.getLessons()
    }
}
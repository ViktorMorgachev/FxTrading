package com.fx_trading.lessons.domain.usecases

import com.fx_trading.lessons.domain.entities.lesson.Lesson
import com.fx_trading.lessons.domain.repositories.LessonRepository
import javax.inject.Inject


class LessonsUseCase @Inject constructor(private val lessonRepository: LessonRepository){
    
   suspend fun getLessons(): List<Lesson>{
        return lessonRepository.getLessons()
    }

    suspend fun getLessonsByID(lessonID: Long): Lesson? {
        return lessonRepository.getLessonByID(lessonID)
    }
}
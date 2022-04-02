package com.learning.lessons.domain.usecases

import com.learning.lessons.domain.entities.lesson.Lesson
import com.learning.lessons.domain.repositories.LessonRepository
import javax.inject.Inject


class LessonsUseCase @Inject constructor(private val lessonRepository: LessonRepository){
    
   suspend fun getLessons(): List<Lesson>{
        return lessonRepository.getLessons()
    }

    suspend fun getLessonsByIDs(lessons_ids: List<Int>): List<Lesson>{
        return lessonRepository.getLessonsByIDS(lessonsIDS = lessons_ids)
    }

    suspend fun getLessonByID(lessonID: Int): Lesson? {
        return lessonRepository.getLessonByID(lessonID)
    }

   suspend fun getLessonsByTags(tags: List<String>): List<Lesson> {
        return lessonRepository.getLessonsByTags(tags)
    }
}
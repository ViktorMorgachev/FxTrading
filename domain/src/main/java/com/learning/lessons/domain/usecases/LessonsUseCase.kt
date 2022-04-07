package com.learning.lessons.domain.usecases

import com.learning.lessons.domain.entities.lesson.Lesson
import com.learning.lessons.domain.entities.webinar.Webinar
import com.learning.lessons.domain.repositories.LessonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject


class LessonsUseCase @Inject constructor(private val lessonRepository: LessonRepository){

    suspend fun getLessonsFlow(): MutableStateFlow<List<Lesson>> {
        return lessonRepository.getLessonsFlow()
    }
    
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
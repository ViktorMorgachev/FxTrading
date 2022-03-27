package com.learning.lessons.data.repositories.lessons

import com.learning.lessons.data.mappers.toLesson
import com.learning.lessons.data.store.LessonsDataSource
import com.learning.lessons.domain.entities.lesson.Lesson
import com.learning.lessons.domain.repositories.LessonRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LessonProvider @Inject constructor(private val lessonsDataSource: LessonsDataSource): LessonRepository {

    override suspend fun getLessons(): List<Lesson> {
         return lessonsDataSource.getLessons().map { it.toLesson() }
    }

    override suspend fun setLikeToLesson(lessonID: Int): Boolean {
        return lessonsDataSource.setLikesLesson(lessonID)
    }

    override suspend fun setDislikeToLesson(lessonID: Int): Boolean {
        return lessonsDataSource.setDislikeLesson(lessonID)
    }

    override suspend fun getLessonByID(lessonID: Int): Lesson? {
      return  lessonsDataSource.getLessonByID(lessonID)?.toLesson()
    }

    override suspend fun getLessonsByTags(tags: List<String>): List<Lesson> {
        return  lessonsDataSource.getLessons().map { it.toLesson() }.filter {
            it.tags.forEach {
                if(tags.contains(it)){
                    return@filter true
                }
            }
            false
        }
    }
}
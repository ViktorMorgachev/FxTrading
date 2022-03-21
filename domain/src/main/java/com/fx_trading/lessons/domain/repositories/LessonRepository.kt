package com.fx_trading.lessons.domain.repositories

import com.fx_trading.lessons.domain.entities.lesson.Lesson

interface LessonRepository {
    suspend fun getLessons(): List<Lesson>
    suspend fun setLikeToLesson(lessonID: Long): Boolean
    suspend fun setDislikeToLesson(lessonID: Long): Boolean
    suspend fun getLessonByID(lessonID: Long): Lesson?
   suspend  fun getLessonsByTags(tags: List<String>): List<Lesson>
}
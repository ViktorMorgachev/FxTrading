package com.fx_trading.lessons.domain.repositories

import com.fx_trading.lessons.domain.entities.lesson.Lesson

interface LessonRepository {
    suspend fun getLessons(): List<Lesson>
    suspend fun setLikeToLesson(lessonID: Int): Boolean
    suspend fun setDislikeToLesson(lessonID: Int): Boolean
    suspend fun getLessonByID(lessonID: Int): Lesson?
    suspend  fun getLessonsByTags(tags: List<String>): List<Lesson>
}
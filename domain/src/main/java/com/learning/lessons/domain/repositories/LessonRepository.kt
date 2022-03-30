package com.learning.lessons.domain.repositories

import com.learning.lessons.domain.entities.lesson.Lesson
import kotlinx.coroutines.Deferred

interface LessonRepository {
    suspend fun getLessons(): List<Lesson>
    suspend fun getLessonByID(lessonID: Int): Lesson?
    suspend fun getLessonsByTags(tags: List<String>): List<Lesson>
    suspend fun getLessonsByIDS(lessonsIDS: List<Int>)
    suspend fun updateLessonField(lessonID: Int, fieldValue: Any, field: String): Deferred<Boolean>
}
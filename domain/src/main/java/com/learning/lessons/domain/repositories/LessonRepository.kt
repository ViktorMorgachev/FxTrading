package com.learning.lessons.domain.repositories

import com.learning.lessons.domain.entities.lesson.Lesson
import kotlinx.coroutines.flow.MutableStateFlow

interface LessonRepository: FieldsUpdateable {
    suspend fun getLessons(): List<Lesson>
    suspend fun getLessonByID(lessonID: Int): Lesson?
    suspend fun getLessonsByTags(tags: List<String>): List<Lesson>
    suspend fun getLessonsByIDS(lessonsIDS: List<Int>): List<Lesson>
    suspend fun getLessonsFlow(): MutableStateFlow<List<Lesson>>
}
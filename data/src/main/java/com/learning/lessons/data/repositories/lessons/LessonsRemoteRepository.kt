package com.learning.lessons.data.repositories.lessons

import com.learning.lessons.data.api.lesson.ApiLesson
import kotlinx.coroutines.flow.Flow

interface LessonsRemoteRepository {
    suspend fun getRemoteLessons(): List<ApiLesson>
    suspend fun getRemoteLessonByID(id: Int): ApiLesson?
    suspend fun updateLessonField(lessonID: Int, fieldValue: Any, field: String): Flow<Boolean>
}
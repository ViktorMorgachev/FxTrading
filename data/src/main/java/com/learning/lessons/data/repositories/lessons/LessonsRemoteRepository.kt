package com.learning.lessons.data.repositories.lessons

import com.learning.lessons.data.api.lesson.ApiLesson
import com.learning.lessons.data.repositories.AutoUpdatable
import com.learning.lessons.data.repositories.FieldsUpdateable
import kotlinx.coroutines.flow.Flow

interface LessonsRemoteRepository: FieldsUpdateable, AutoUpdatable {
    suspend fun getRemoteLessons(): List<ApiLesson>
    suspend fun getRemoteLessonByID(id: Int): ApiLesson?
}
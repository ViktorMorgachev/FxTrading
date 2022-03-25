package com.fx_trading.lessons.data.repositories.lessons

import com.fx_trading.lessons.data.api.lesson.ApiLesson

interface LessonsRemoteRepository {
    suspend  fun getRemoteLessons(): List<ApiLesson>
    suspend fun getRemoteLessonByID(id: Int): ApiLesson?
    suspend fun updateLesson(lesson: ApiLesson): Boolean
}
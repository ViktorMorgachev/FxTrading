package com.fx_trading.lessons.data.repositories.lessons

import com.fx_trading.lessons.data.api.lesson.Lesson

interface GetLessonsRemote {
    suspend fun getRemoteLessons(): List<Lesson>
}
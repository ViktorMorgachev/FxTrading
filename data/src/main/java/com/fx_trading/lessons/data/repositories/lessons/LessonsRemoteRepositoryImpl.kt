package com.fx_trading.lessons.data.repositories.lessons

import com.fx_trading.lessons.data.api.lesson.Lesson

class LessonsRemoteRepositoryImpl: LessonsRemoteRepository {

    override suspend fun getRemoteLessons(): List<Lesson> {
        TODO("Not yet implemented")
    }
}
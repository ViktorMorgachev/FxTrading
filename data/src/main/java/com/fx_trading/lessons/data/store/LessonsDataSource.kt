package com.fx_trading.lessons.data.store

import com.fx_trading.lessons.data.api.lesson.ApiLesson
import com.fx_trading.lessons.data.api.question_group.ApiQuestionGroup
import com.fx_trading.lessons.data.mappers.toLesson
import com.fx_trading.lessons.data.repositories.lessons.LessonsRemoteRepository
import com.fx_trading.lessons.data.repositories.question.QuestionRemoteRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LessonsDataSource @Inject constructor(protected val lessonsRemoteRepository: LessonsRemoteRepository): LessonsRemoteRepository {

    override suspend fun getRemoteLessons(): List<ApiLesson> {
       return lessonsRemoteRepository.getRemoteLessons()
    }

    override suspend fun getRemoteLessonByID(id: Int): ApiLesson {
       return lessonsRemoteRepository.getRemoteLessonByID(id)
    }

    suspend fun getLessons(): List<ApiLesson>{
        return getRemoteLessons()
    }

    suspend fun getLessonsByID(id: Int): ApiLesson{
        return getRemoteLessonByID(id)
    }

}
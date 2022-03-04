package com.fx_trading.lessons.data.repositories.question

import com.fx_trading.lessons.data.api.quiz.ApiQuizGroup
import com.fx_trading.lessons.domain.entities.quiz.Questions


interface QuestionRemoteRepository {
    suspend fun getRemoteQuestions(): List<ApiQuizGroup>
    suspend fun getRemoteQuestionsByID(id: Int): Questions
}
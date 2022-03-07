package com.fx_trading.lessons.data.repositories.question

import com.fx_trading.lessons.data.api.quiz.ApiQuizGroup
import com.fx_trading.lessons.domain.entities.quiz.Questions


interface QuestionRemoteDevRepository {
    suspend fun getRemoteQuestions(): List<ApiQuizGroup>
    suspend fun getRemoteQuestions(ids: List<Int>): Questions
}
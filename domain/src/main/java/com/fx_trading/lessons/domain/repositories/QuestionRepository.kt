package com.fx_trading.lessons.domain.repositories

import com.fx_trading.lessons.domain.entities.quiz.Questions

interface QuestionRepository {
    suspend fun getQuestions(): List<Questions>
    suspend fun getQuestionsByID(id: Int): Questions
}
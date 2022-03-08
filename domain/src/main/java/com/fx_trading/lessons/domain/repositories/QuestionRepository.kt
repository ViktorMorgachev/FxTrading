package com.fx_trading.lessons.domain.repositories

import com.fx_trading.lessons.domain.entities.quiz.Question
import com.fx_trading.lessons.domain.entities.quiz.Questions

interface QuestionRepository {
    suspend fun getQuestions(): List<Question>
    suspend fun getQuestionsByID(id: Int): Questions
}
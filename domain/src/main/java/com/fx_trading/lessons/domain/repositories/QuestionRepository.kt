package com.fx_trading.lessons.domain.repositories

import com.fx_trading.lessons.domain.entities.quiz.QuestionsGroup


interface QuestionRepository {
    suspend fun getQuestionGroups(): List<QuestionsGroup>
    suspend fun getQuestionsGroup(id: Int): QuestionsGroup
}
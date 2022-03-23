package com.fx_trading.lessons.domain.repositories

import com.fx_trading.lessons.domain.entities.quiz.QuestionsGroup
import kotlinx.coroutines.flow.Flow


interface QuestionRepository {
    suspend fun getQuestionsGroups(): List<QuestionsGroup>
    suspend fun getQuestionsGroup(id: Int): QuestionsGroup?
    suspend fun getStartQuestionGroup(): QuestionsGroup?
}
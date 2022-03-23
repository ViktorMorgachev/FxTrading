package com.fx_trading.lessons.domain.usecases

import com.fx_trading.lessons.domain.entities.quiz.QuestionsGroup
import com.fx_trading.lessons.domain.repositories.QuestionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuestionUseCase @Inject constructor(private val questionProvider: QuestionRepository) {

    suspend fun getQuestionsGroup(id: Int): QuestionsGroup? {
        return questionProvider.getQuestionsGroup(id)
    }

    suspend fun getStartQuestionGroup(): QuestionsGroup?{
        return questionProvider.getStartQuestionGroup()
    }

}
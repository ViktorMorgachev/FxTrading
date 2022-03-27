package com.learning.lessons.domain.usecases

import com.learning.lessons.domain.entities.quiz.QuestionsGroup
import com.learning.lessons.domain.repositories.QuestionRepository
import javax.inject.Inject

class QuestionUseCase @Inject constructor(private val questionProvider: QuestionRepository) {

    suspend fun getQuestionsGroup(id: Int): QuestionsGroup? {
        return questionProvider.getQuestionsGroup(id)
    }

    suspend fun getStartQuestionGroup(): QuestionsGroup?{
        return questionProvider.getStartQuestionGroup()
    }

}
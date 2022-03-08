package com.fx_trading.lessons.domain.usecases

import com.fx_trading.lessons.domain.entities.quiz.QuestionsGroup
import com.fx_trading.lessons.domain.repositories.QuestionRepository
import javax.inject.Inject

class QuestionUseCase @Inject constructor(private val questionRepository: QuestionRepository) {

    suspend fun getQuestionGroupById(quizID: Int): QuestionsGroup {
        return questionRepository.getQuestionsGroup(quizID)
    }

    suspend fun getQuestionStartExamQuestionGroup(): QuestionsGroup {
        return questionRepository.getQuestionGroups().first { it.isStartExam }
    }

    fun test(){

    }
}
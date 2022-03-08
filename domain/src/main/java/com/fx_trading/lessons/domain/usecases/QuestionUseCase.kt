package com.fx_trading.lessons.domain.usecases

import com.fx_trading.lessons.domain.entities.quiz.Questions
import com.fx_trading.lessons.domain.repositories.QuestionRepository
import javax.inject.Inject

class QuestionUseCase @Inject constructor(private val questionRepository: QuestionRepository) {

    suspend fun getQuestionsById(quizID: Int): Questions {
        return questionRepository.getQuestionsByID(quizID)
    }
    fun test(){

    }
}
package com.fx_trading.lessons.domain.usecases

import com.fx_trading.lessons.domain.entities.quiz.Questions
import com.fx_trading.lessons.domain.repositories.QuestionRepository
import javax.inject.Inject

class QuestionUseCase @Inject constructor(private val questionRepository: QuestionRepository) {

    suspend fun getQuizById(quizID: Int): Questions {
        return questionRepository.getQuestionsByID(quizID)
    }
    suspend fun getQuizzes(): List<Questions>{
        return questionRepository.getQuestions()
    }
}
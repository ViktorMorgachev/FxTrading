package com.fx_trading.lessons.data.repositories.quiz

import com.fx_trading.lessons.data.mappers.toQuizGroup
import com.fx_trading.lessons.domain.entities.quiz.Questions
import com.fx_trading.lessons.domain.repositories.QuestionRepository

class QuestionRepositoryImpl(private val questionRemoteRepository: QuestionRemoteRepository) : QuestionRepository {

    override suspend fun getQuestions(): List<Questions> {
        return questionRemoteRepository.getRemoteQuestions().map { it.toQuizGroup() }
    }

    override suspend fun getQuestionsByID(id: Int): Questions {
        return questionRemoteRepository.getRemoteQuestionsByID(id)
    }

}
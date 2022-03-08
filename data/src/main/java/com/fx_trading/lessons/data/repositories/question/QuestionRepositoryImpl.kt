package com.fx_trading.lessons.data.repositories.question

import com.fx_trading.lessons.data.mappers.toQuestionGroup
import com.fx_trading.lessons.domain.entities.quiz.QuestionsGroup
import com.fx_trading.lessons.domain.repositories.QuestionRepository
import javax.inject.Inject

class QuestionRepositoryImpl @Inject constructor(private val questionRemoteRepository: QuestionRemoteRepository) : QuestionRepository {

    override suspend fun getQuestionGroups(): List<QuestionsGroup> {
        return questionRemoteRepository.getRemoteQuestionGroups().map { it.toQuestionGroup() }
    }

    override suspend fun getQuestionsGroup(id: Int): QuestionsGroup {
        return questionRemoteRepository.getRemoteQuestionGroup(id).toQuestionGroup()
    }

}
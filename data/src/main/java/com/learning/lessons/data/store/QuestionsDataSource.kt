package com.learning.lessons.data.store

import com.learning.lessons.data.mappers.toQuestionGroup
import com.learning.lessons.data.repositories.question.QuestionRemoteRepository
import com.learning.lessons.domain.entities.quiz.QuestionsGroup
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuestionsDataSource @Inject constructor(private val questionRemoteRepository: QuestionRemoteRepository) {

    suspend fun getQuestionsGroups(): List<QuestionsGroup> {
        return questionRemoteRepository.getRemoteQuestionGroups().map { it.toQuestionGroup() }
    }

    suspend fun getQuestionsGroup(id: Int): QuestionsGroup? {
        return questionRemoteRepository.getRemoteQuestionGroup(id)?.toQuestionGroup()
    }

    suspend fun getStartQuestionGroup(): QuestionsGroup? {
        return questionRemoteRepository.getStartQuestionGroup()?.toQuestionGroup()
    }

}
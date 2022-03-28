package com.learning.lessons.data.store

import com.learning.lessons.data.mappers.toQuestionGroup
import com.learning.lessons.data.repositories.question.QuestionRemoteRepository
import com.learning.lessons.domain.entities.quiz.QuestionsGroup
import com.learning.lessons.domain.repositories.QuestionRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuestionsDataSource @Inject constructor(private val questionRemoteRepository: QuestionRemoteRepository): QuestionRepository {

    override suspend fun getQuestionsGroups(): List<QuestionsGroup> {
     return questionRemoteRepository.getRemoteQuestionGroups().map { it.toQuestionGroup() }
    }

    override suspend fun getQuestionsGroup(id: Int): QuestionsGroup? {
      return questionRemoteRepository.getRemoteQuestionGroup(id)?.toQuestionGroup()
    }

    override suspend fun getStartQuestionGroup(): QuestionsGroup? {
        return questionRemoteRepository.getStartQuestionGroup()?.toQuestionGroup()
    }

}
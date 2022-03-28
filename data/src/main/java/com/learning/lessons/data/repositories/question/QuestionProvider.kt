package com.learning.lessons.data.repositories.question


import com.learning.lessons.data.mappers.toQuestionGroup
import com.learning.lessons.data.store.QuestionsDataSource
import com.learning.lessons.domain.entities.quiz.QuestionsGroup
import com.learning.lessons.domain.repositories.QuestionRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuestionProvider @Inject constructor(private val questionDataSource: QuestionsDataSource): QuestionRepository  {

    override suspend fun getQuestionsGroups(): List<QuestionsGroup> {
        return questionDataSource.getQuestionsGroups()
    }

    override suspend fun getQuestionsGroup(id: Int): QuestionsGroup? {
        return questionDataSource.getQuestionsGroup(id)
    }

    override suspend fun getStartQuestionGroup(): QuestionsGroup? {
        return questionDataSource.getStartQuestionGroup()
    }
}
package com.fx_trading.lessons.data.repositories.question


import com.fx_trading.lessons.data.mappers.toQuestionGroup
import com.fx_trading.lessons.data.store.QuestionsDataSource
import com.fx_trading.lessons.domain.entities.quiz.QuestionsGroup
import com.fx_trading.lessons.domain.repositories.QuestionRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuestionProvider @Inject constructor(private val questionDataSource: QuestionsDataSource): QuestionRepository  {

    override suspend fun getQuestionsGroups(): List<QuestionsGroup> {
        return questionDataSource.getQuestionGroups().mapNotNull { it.toQuestionGroup() }
    }

    override suspend fun getQuestionsGroup(id: Int): QuestionsGroup? {
        return questionDataSource.getQuestionGroup(id).toQuestionGroup()
    }

    override suspend fun getStartQuestionGroup(): QuestionsGroup? {
        return questionDataSource.getStartQuestionGroup().toQuestionGroup()
    }
}
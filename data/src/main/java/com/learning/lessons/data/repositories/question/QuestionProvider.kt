package com.learning.lessons.data.repositories.question


import com.learning.lessons.data.store.QuestionsDataSource
import com.learning.lessons.domain.entities.quiz.QuestionsGroup
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuestionProvider @Inject constructor(private val questionDataSource: QuestionsDataSource)  {

     suspend fun getQuestionsGroups(): List<QuestionsGroup> {
        return questionDataSource.getQuestionsGroups()
    }

     suspend fun getQuestionsGroup(id: Int): QuestionsGroup? {
        return questionDataSource.getQuestionsGroup(id)
    }

     suspend fun getStartQuestionGroup(): QuestionsGroup? {
        return questionDataSource.getStartQuestionGroup()
    }
}
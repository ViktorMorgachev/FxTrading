package com.learning.lessons.data.store

import com.learning.lessons.data.api.question_group.ApiQuestionGroup
import com.learning.lessons.data.repositories.question.QuestionRemoteRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuestionsDataSource @Inject constructor(protected val questionRemoteRepository: QuestionRemoteRepository) {


    suspend fun getQuestionGroups(): List<ApiQuestionGroup> {
        return  questionRemoteRepository.getRemoteQuestionGroups()
    }

    suspend fun getQuestionGroup(id: Int): ApiQuestionGroup? {
        return questionRemoteRepository.getRemoteQuestionGroup(id)
    }

    suspend fun getStartQuestionGroup(): ApiQuestionGroup? {
        return questionRemoteRepository.getStartQuestionGroup()
    }

}
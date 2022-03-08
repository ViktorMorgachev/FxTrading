package com.fx_trading.lessons.data.store

import com.fx_trading.lessons.data.api.question_group.ApiQuestionGroup
import com.fx_trading.lessons.data.repositories.question.QuestionRemoteRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuestionsDataSource @Inject constructor(protected val questionRemoteRepository: QuestionRemoteRepository) {

     private suspend fun getRemoteQuestionGroups(): List<ApiQuestionGroup> {
       return questionRemoteRepository.getRemoteQuestionGroups()
    }

    private suspend fun getRemoteQuestionGroup(id: Int): ApiQuestionGroup {
         return questionRemoteRepository.getRemoteQuestionGroup(id)
    }

    suspend fun getQuestionGroups(): List<ApiQuestionGroup> {
        return getRemoteQuestionGroups()
    }

    suspend fun getQuestionGroup(id: Int): ApiQuestionGroup {
        return getRemoteQuestionGroup(id)
    }

}
package com.fx_trading.lessons.data.store

import com.fx_trading.lessons.data.api.question_group.ApiQuestionGroup
import com.fx_trading.lessons.data.repositories.question.QuestionRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuestionsDataSource @Inject constructor(protected val questionRemoteRepository: QuestionRemoteRepository) {

     private suspend fun getRemoteQuestionGroups(): Flow<List<ApiQuestionGroup>> {
       return questionRemoteRepository.getRemoteQuestionGroups()
    }

    private suspend fun getRemoteQuestionGroup(id: Int): Flow<ApiQuestionGroup?> {
         return questionRemoteRepository.getRemoteQuestionGroup(id)
    }

    suspend fun getQuestionGroups(): Flow<List<ApiQuestionGroup>> {
        return getRemoteQuestionGroups()
    }

    suspend fun getQuestionGroup(id: Int): Flow<ApiQuestionGroup?> {
        return getRemoteQuestionGroup(id)
    }
    suspend fun getFirstExamQuestionGroup(): Flow<ApiQuestionGroup?> {
        return questionRemoteRepository.getFirstExamRemoteQuestionGroup()
    }

}
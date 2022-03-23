package com.fx_trading.lessons.data.repositories.question

import com.fx_trading.lessons.data.api.question_group.ApiQuestionGroup
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow


interface QuestionRemoteRepository {
    suspend fun getRemoteQuestionGroups(): List<ApiQuestionGroup>
    suspend fun getRemoteQuestionGroup(id: Int): ApiQuestionGroup?
    suspend fun getStartQuestionGroup(): ApiQuestionGroup?
}
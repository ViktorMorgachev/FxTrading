package com.fx_trading.lessons.data.repositories.question

import com.fx_trading.lessons.data.api.question_group.ApiQuestionGroup


interface QuestionRemoteRepository {
    suspend fun getRemoteQuestionGroups(): List<ApiQuestionGroup>
    suspend fun getRemoteQuestionGroup(id: Int): ApiQuestionGroup
}
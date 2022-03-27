package com.learning.lessons.data.repositories.question

import com.learning.lessons.data.api.question_group.ApiQuestionGroup


interface QuestionRemoteRepository {
    suspend fun getRemoteQuestionGroups(): List<ApiQuestionGroup>
    suspend fun getRemoteQuestionGroup(id: Int): ApiQuestionGroup?
    suspend fun getStartQuestionGroup(): ApiQuestionGroup?
}
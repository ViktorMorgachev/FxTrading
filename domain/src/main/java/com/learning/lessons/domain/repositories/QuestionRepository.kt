package com.learning.lessons.domain.repositories

import com.learning.lessons.domain.entities.quiz.QuestionsGroup


interface QuestionRepository {
    suspend fun getQuestionsGroups(): List<QuestionsGroup>
    suspend fun getQuestionsGroup(id: Int): QuestionsGroup?
    suspend fun getStartQuestionGroup(): QuestionsGroup?
}
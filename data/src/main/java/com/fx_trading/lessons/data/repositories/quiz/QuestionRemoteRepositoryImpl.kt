package com.fx_trading.lessons.data.repositories.quiz

import com.fx_trading.lessons.data.api.quiz.ApiQuizGroup
import com.fx_trading.lessons.domain.entities.quiz.Questions
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class QuestionRemoteRepositoryImpl: QuestionRemoteRepository {

    @Inject
    lateinit var firebaseFireStore: FirebaseFirestore

    override suspend fun getRemoteQuestions(): List<ApiQuizGroup> {
        TODO("Not yet implemented")
    }

    override suspend fun getRemoteQuestionsByID(id: Int): Questions {
        TODO("Not yet implemented")
    }
}
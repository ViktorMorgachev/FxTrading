package com.fx_trading.lessons.data.repositories.question

import com.fx_trading.lessons.core.utils.Logger
import com.fx_trading.lessons.data.api.quiz.ApiQuizGroup
import com.fx_trading.lessons.domain.entities.quiz.Questions
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject


class QuestionRemoteRepositoryImpl : QuestionRemoteRepository {

    @Inject
    lateinit var firebaseFireStore: FirebaseFirestore

    override suspend fun getRemoteQuestions(): List<ApiQuizGroup> {
        firebaseFireStore.collection("quiz_group")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    task.result?.let { result ->
                        for (document in result) {
                            Logger.log(
                                "QuestionRemoteRepository",
                                "Data ${document.id + " => " + document.data}"
                            )
                        }
                    }

                } else {
                    Logger.log(
                        "QuestionRemoteRepository",
                        "Error getting documents.",
                        task.exception
                    )
                }
            }.addOnFailureListener {
                Logger.log("QuestionRemoteRepository", "Error getting documents.", it)
            }
        return emptyList()
    }

    override suspend fun getRemoteQuestionsByID(id: Int): Questions {
        firebaseFireStore.collection("quiz_group").document("/${id}")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    task.result?.let { result ->
                        Logger.log(
                            "QuestionRemoteRepository",
                            "Data ${result.id + " => " + result.data}"
                        )
                    }
                } else {
                    Logger.log(
                        "QuestionRemoteRepository",
                        "Error getting documents.",
                        task.exception
                    )
                }
            }
        TODO("Not yet implemented")
    }
}
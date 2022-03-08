package com.fx_trading.lessons.data.repositories.question

import com.fx_trading.lessons.core.utils.Logger
import com.fx_trading.lessons.data.api.question_group.ApiQuestionGroup
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject


class QuestionRemoteDevRepositoryImpl: QuestionRemoteRepository{
    @Inject
    lateinit var firebaseFireStore: FirebaseFirestore

    override suspend fun getRemoteQuestionGroups(): List<ApiQuestionGroup> {
        firebaseFireStore.collection("exams").document("/dev")
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
            }.addOnFailureListener {
                Logger.log("QuestionRemoteRepository", "Error getting documents.", it)
            }
        return emptyList()
    }

    override suspend fun getRemoteQuestionGroup(id: Int): ApiQuestionGroup {
        firebaseFireStore.collection("exams").document("/dev").get()
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
package com.fx_trading.lessons.data.repositories.question

import com.fx_trading.lessons.data.api.question_group.ApiQuestionGroup
import com.fx_trading.lessons.utils.utils.Logger
import com.google.firebase.firestore.BuildConfig
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

val documentPath = if (BuildConfig.DEBUG) "dev" else "prod"

class QuestionRemoteRepositoryImpl @Inject constructor() : QuestionRemoteRepository {

    @Inject
    lateinit var firebaseFireStore: FirebaseFirestore

    override suspend fun getRemoteQuestionGroups(): List<ApiQuestionGroup> {
        firebaseFireStore.collection("exams").document(documentPath)
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
        firebaseFireStore.collection("exams").document("/${id}")
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
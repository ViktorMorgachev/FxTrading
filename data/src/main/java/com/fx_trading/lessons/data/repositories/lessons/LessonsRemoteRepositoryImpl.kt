package com.fx_trading.lessons.data.repositories.lessons

import com.fx_trading.lessons.data.api.lesson.ApiLesson
import com.fx_trading.lessons.utils.utils.Logger
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class LessonsRemoteRepositoryImpl @Inject constructor(): LessonsRemoteRepository {

    @Inject
    lateinit var firebaseFireStore: FirebaseFirestore

    override suspend fun getRemoteLessons(): List<ApiLesson> {
        firebaseFireStore.collection("lessons")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    task.result?.let { result ->
                        for (document in result) {
                            Logger.log(
                                "LessonsRemoteRepository",
                                "Data ${document.id + " => " + document.data}"
                            )
                        }
                    }

                } else {
                    Logger.log(
                        "LessonsRemoteRepository",
                        "Error getting documents.",
                        task.exception
                    )
                }
            }.addOnFailureListener {
                Logger.log(
                    "LessonsRemoteRepository",
                    "Error getting documents.",
                    it
                )
            }
        return emptyList()
    }

    override suspend fun getRemoteLessonByID(id: Int): ApiLesson {
        firebaseFireStore.collection("lessons").document("/${id}")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    task.result?.let { result ->
                        Logger.log(
                            "LessonsRemoteRepository",
                            "Data ${result.id + " => " + result.data}"
                        )
                    }
                } else {
                    Logger.log(
                        "LessonsRemoteRepository",
                        "Error getting documents.",
                        task.exception
                    )
                }
            }
        TODO("Not yet implemented")
    }
}
package com.fx_trading.lessons.data.repositories.lessons

import com.fx_trading.lessons.data.BuildConfig
import com.fx_trading.lessons.data.api.lesson.ApiLesson
import com.fx_trading.lessons.data.api.user.ApiUser
import com.fx_trading.lessons.data.extentions.await
import com.fx_trading.lessons.utils.utils.Logger
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class LessonsRemoteRepositoryImpl @Inject constructor(private val firebaseFirestore: FirebaseFirestore): LessonsRemoteRepository {

    private val documentPath = BuildConfig.DOCUMENT_DB_PATH

    override suspend fun getRemoteLessons(): List<ApiLesson> {
        try {
            val firebaseDocuments = firebaseFirestore.collection("${documentPath}Lessons").get().await()
            if (firebaseDocuments != null && !firebaseDocuments.isEmpty) {
                    val apiLessons = firebaseDocuments.documents.mapNotNull {
                        try {
                            it.toObject(ApiLesson::class.java)
                        } catch (e: Exception){
                            Logger.log("LessonsRemoteRepository", ".toObject(ApiLesson::class.java) DocumentID ${it.id}")
                            Logger.log("LessonsRemoteRepository", exception =  e)
                            null
                        }
                    }


                Logger.log("LessonsRemoteRepository", "Data ${firebaseDocuments.documents}")
                return apiLessons.filter { it.active }
            } else {
                Logger.log("LessonsRemoteRepository", "Error getting documents.")
            }
            return listOf()
        } catch (e: Exception) {
            Logger.log("LessonsRemoteRepository", "Error getting documents.", exception = e)
            return listOf()
        }
    }

    override suspend fun getRemoteLessonByID(id: Long): ApiLesson? {
        try {
            val firebaseDocument = firebaseFirestore.collection("${documentPath}Lessons").document("$id").get().await()
            if (firebaseDocument != null && !firebaseDocument.data.isNullOrEmpty()) {
                return  firebaseDocument.toObject(ApiLesson::class.java)
            } else {
                Logger.log("LessonsRemoteRepository", "Error getting documents.")
                return null
            }
        } catch (e: Exception) {
            Logger.log("LessonsRemoteRepository", "Error getting documents.", exception = e)
            return null
        }
    }

    override suspend fun updateLesson(lesson: ApiLesson): Boolean {
        try {
            firebaseFirestore.collection("${documentPath}Lessons").document("${lesson.id}").set(lesson).await()
            return true
        } catch (e: Exception) {
            Logger.log("LessonsRemoteRepository", "Error getting documents.", exception = e)
            return false
        }
    }
}
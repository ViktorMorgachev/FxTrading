package com.fx_trading.lessons.data.repositories.lessons

import com.fx_trading.lessons.data.BuildConfig
import com.fx_trading.lessons.data.api.lesson.ApiLesson
import com.fx_trading.lessons.data.api.user.ApiUser
import com.fx_trading.lessons.data.extentions.await
import com.fx_trading.lessons.utils.utils.Logger
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

val documentPath = BuildConfig.DOCUMENT_DB_PATH

class LessonsRemoteRepositoryImpl @Inject constructor(private val firebaseFirestore: FirebaseFirestore): LessonsRemoteRepository {

    @Inject
    lateinit var firebaseFireStore: FirebaseFirestore

    override suspend fun getRemoteLessons(): List<ApiLesson> {
        try {
            val firebaseDocuments = firebaseFirestore.collection("${documentPath}Lessons").get().await()
            if (firebaseDocuments != null && !firebaseDocuments.isEmpty) {
                val apiLessons = firebaseDocuments.documents.mapNotNull { it.toObject(ApiLesson::class.java) }
                Logger.log("LessonsRemoteRepository", "Data ${firebaseDocuments.documents}")
                return apiLessons
            } else {
                Logger.log("LessonsRemoteRepository", "Error getting documents.")
            }
            return listOf()
        } catch (e: Exception) {
            Logger.log("LessonsRemoteRepository", "Error getting documents.", exception = e)
            return listOf()
        }
    }

    override suspend fun getRemoteLessonByID(id: Int): ApiLesson {
        TODO("Not yet implemented")
    }
}
package com.fx_trading.lessons.data.repositories.webinars

import com.fx_trading.lessons.data.BuildConfig
import com.fx_trading.lessons.data.api.lesson.ApiLesson
import com.fx_trading.lessons.data.api.webinar.ApiWebinar
import com.fx_trading.lessons.data.extentions.await
import com.fx_trading.lessons.utils.utils.Logger
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class WebinarsRemoteRepositoryImpl @Inject constructor(private val firebaseFirestore: FirebaseFirestore) : WebinarsRemoteRepository {

    private val documentPath = BuildConfig.DOCUMENT_DB_PATH

    override suspend fun getWebinars(): List<ApiWebinar> {
        try {
            val firebaseDocuments = firebaseFirestore.collection("${documentPath}Webinars").get().await()
            if (firebaseDocuments != null && !firebaseDocuments.isEmpty) {
                val apiLessons = firebaseDocuments.documents.mapNotNull {
                    try {
                        it.toObject(ApiWebinar::class.java)
                    } catch (e: Exception){
                        Logger.log("WebinarsRemoteRepository", ".toObject(ApiWebinar::class.java)", exception = e)
                        null
                    }
                }
                Logger.log("WebinarsRemoteRepository", "Data ${firebaseDocuments.documents}")
                return apiLessons.filter { it.active }
            } else {
                Logger.log("WebinarsRemoteRepository", "Error getting documents.")
            }
            return listOf()
        } catch (e : Exception){
            Logger.log("LessonsRemoteRepository", "Error getting documents.", exception = e)
            return listOf()
        }
    }

    override suspend fun getWebinarByID(id: Long): ApiWebinar {
        TODO("Not yet implemented")
    }

    override suspend fun updateWebinar(webinar: ApiWebinar) {
        TODO("Not yet implemented")
    }
}
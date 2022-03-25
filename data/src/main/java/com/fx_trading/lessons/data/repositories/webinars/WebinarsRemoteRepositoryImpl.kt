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
            Logger.log("WebinarsRemoteRepository", "Error getting documents.", exception = e)
            return listOf()
        }
    }

    override suspend fun getWebinarByID(id: Int): ApiWebinar? {
        try {
            val firebaseDocument = firebaseFirestore.collection("${documentPath}Webinars").document("$id").get().await()
            if (firebaseDocument != null && !firebaseDocument.data.isNullOrEmpty()) {
                return  firebaseDocument.toObject(ApiWebinar::class.java)
            } else {
                Logger.log("WebinarsRemoteRepository", "Error getting documents.")
                return null
            }
        } catch (e: Exception) {
            Logger.log("WebinarsRemoteRepository", "Error getting documents.", exception = e)
            return null
        }
    }

    override suspend fun updateWebinar(webinar: ApiWebinar): Boolean {
        try {
            firebaseFirestore.collection("${documentPath}Webinars").document("${webinar.id}").set(webinar).await()
            return true
        } catch (e: Exception) {
            Logger.log("WebinarsRemoteRepository", "Error update documents.", exception = e)
            return false
        }
    }

    override suspend fun updateWebinarField(webinarID: Int, fieldValue: Any, field: String) {
        TODO("Not yet implemented")
    }
}
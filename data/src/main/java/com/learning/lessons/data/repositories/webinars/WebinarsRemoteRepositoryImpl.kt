package com.learning.lessons.data.repositories.webinars

import com.learning.lessons.data.BuildConfig
import com.learning.lessons.data.api.webinar.ApiWebinar
import com.learning.lessons.data.extentions.await
import com.learning.lessons.utils.utils.Logger
import com.google.firebase.firestore.FirebaseFirestore
import com.learning.lessons.data.extentions.toObjectOrDefault
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WebinarsRemoteRepositoryImpl @Inject constructor(private val firebaseFirestore: FirebaseFirestore) : WebinarsRemoteRepository {

    private val logger_tag = this::class.java.simpleName

    private val documentPath = BuildConfig.DOCUMENT_DB_PATH

    override suspend fun getWebinars(): List<ApiWebinar> {
        return try {
            val firebaseDocuments = firebaseFirestore.collection("${documentPath}Webinars").get().await()
            firebaseDocuments?.documents?.mapNotNull { it.toObjectOrDefault(ApiWebinar::class.java) }?: listOf()
        } catch (e : Exception){
            Logger.log(logger_tag,  exception = e)
            listOf()
        }
    }

    override suspend fun getWebinarsFlow() = flow<List<ApiWebinar>> {
        try {
           emit(listOf())
        } catch (e : Exception){
           Logger.log(logger_tag, exception = e)
        }
    }

    override suspend fun getWebinarByID(id: Int): ApiWebinar? {
        try {
            val firebaseDocument = firebaseFirestore.collection("${documentPath}Webinars").document("$id").get().await()
            if (firebaseDocument != null && !firebaseDocument.data.isNullOrEmpty()) {
                return  firebaseDocument.toObject(ApiWebinar::class.java)
            } else {
                Logger.log(logger_tag, "Error getting documents.")
                return null
            }
        } catch (e: Exception) {
            Logger.log(logger_tag, "Error getting documents.", exception = e)
            return null
        }
    }

    override suspend fun getWebinarByIDFlow(id: Int) = flow<ApiWebinar?> {
        try {
            emit(null)
        } catch (e : Exception){
            Logger.log(logger_tag, exception = e)
        }
    }

    override suspend fun updateWebinarField(webinarID: Int, fieldValue: Any, field: String) {
        TODO("Not yet implemented")
    }
}
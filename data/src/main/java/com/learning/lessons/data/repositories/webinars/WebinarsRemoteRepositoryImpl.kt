package com.learning.lessons.data.repositories.webinars

import com.google.firebase.firestore.FieldValue
import com.learning.lessons.data.BuildConfig
import com.learning.lessons.data.api.webinar.ApiWebinar
import com.learning.lessons.data.extentions.await
import com.learning.lessons.utils.utils.Logger
import com.google.firebase.firestore.FirebaseFirestore
import com.learning.lessons.data.extentions.toObjectOrDefault
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
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
        return try {
            val firebaseDocument = firebaseFirestore.collection("${documentPath}Webinars").document("$id").get().await()
            firebaseDocument?.toObjectOrDefault(ApiWebinar::class.java)
        } catch (e: Exception) {
            Logger.log(logger_tag, exception = e)
            null
        }
    }

    override suspend fun getWebinarByIDFlow(id: Int) = flow<ApiWebinar?> {
        try {
            emit(null)
        } catch (e : Exception){
            Logger.log(logger_tag, exception = e)
        }
    }

    override suspend fun updateWebinarField(webinarID: Int, fieldValue: Any, field: String)= flow<Boolean> {
        try {
            val firebaseDocumentRef = firebaseFirestore.collection("${documentPath}Webinars").document("$webinarID")
            if (fieldValue is  Array<*>) {
                firebaseDocumentRef.update(field, FieldValue.arrayUnion(fieldValue)).addOnSuccessListener {
                    CoroutineScope(Dispatchers.IO).launch {
                        emit(true)
                    }
                }.addOnFailureListener {
                    CoroutineScope(Dispatchers.IO).launch {
                        emit(false)
                    }
                }.addOnFailureListener {
                    CoroutineScope(Dispatchers.IO).launch {
                        emit(false)
                    }
                }
            }
            else {
                firebaseDocumentRef.update(mapOf(field to fieldValue)).addOnSuccessListener {
                    CoroutineScope(Dispatchers.IO).launch {
                        emit(true)
                    }
                }.addOnFailureListener {
                    CoroutineScope(Dispatchers.IO).launch {
                        emit(false)
                    }
                }.addOnCanceledListener {
                    CoroutineScope(Dispatchers.IO).launch {
                        emit(false)
                    }
                }
            }
        } catch (e: Exception) {
            Logger.log(logger_tag, exception = e)
            emit(false)
        }
    }
}
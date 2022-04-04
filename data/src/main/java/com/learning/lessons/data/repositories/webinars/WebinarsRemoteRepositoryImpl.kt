package com.learning.lessons.data.repositories.webinars

import com.learning.lessons.data.api.webinar.ApiWebinar
import com.learning.lessons.data.extentions.await
import com.learning.lessons.utils.utils.Logger
import com.google.firebase.firestore.FirebaseFirestore
import com.learning.lessons.data.BuildConfig
import com.learning.lessons.data.extentions.toObjectOrDefault
import com.learning.lessons.data.repositories.FieldUpdateableRealisation
import com.learning.lessons.data.repositories.FieldsUpdateable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WebinarsRemoteRepositoryImpl @Inject constructor(private val firebaseFirestore: FirebaseFirestore, private val fieldUpdateableRealisation: FieldUpdateableRealisation) : WebinarsRemoteRepository {

      val logger_tag = this::class.java.simpleName
      var documentPath = "${BuildConfig.DOCUMENT_DB_PATH}Webinars"

    init {
        fieldUpdateableRealisation.updateFieldDocumentPath = documentPath
    }

    override suspend fun getWebinars(): List<ApiWebinar> {
        return try {
            val firebaseDocuments = firebaseFirestore.collection(documentPath).get().await()
            firebaseDocuments?.documents?.mapNotNull { it.toObjectOrDefault(ApiWebinar::class.java) }?.filter { it.active } ?: listOf()
        } catch (e : Exception){
            Logger.log(logger_tag,  exception = e)
            listOf()
        }
    }


    override suspend fun getWebinarByID(id: Int): ApiWebinar? {
        return try {
            val firebaseDocument = firebaseFirestore.collection(documentPath).document("$id").get().await()
            firebaseDocument?.toObjectOrDefault(ApiWebinar::class.java)
        } catch (e: Exception) {
            Logger.log(logger_tag, exception = e)
            null
        }
    }

    override suspend fun updateFields(
        objectID: Int,
        fieldValues: List<Pair<String, Any>>
    ): Flow<Boolean> {
      return  fieldUpdateableRealisation.updateFields(objectID, fieldValues)
    }
}
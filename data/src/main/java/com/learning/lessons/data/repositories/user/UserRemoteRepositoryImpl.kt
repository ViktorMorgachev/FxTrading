package com.learning.lessons.data.repositories.user

import com.learning.lessons.data.BuildConfig
import com.learning.lessons.data.api.user.ApiUser
import com.learning.lessons.data.extentions.await
import com.learning.lessons.utils.utils.Logger
import com.google.firebase.firestore.FirebaseFirestore
import com.learning.lessons.data.extentions.toObjectOrDefault
import com.learning.lessons.data.repositories.FieldUpdateableRealisation
import com.learning.lessons.data.repositories.FieldsUpdateable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject



class UserRemoteRepositoryImpl @Inject constructor(
    val firebaseFirestore: FirebaseFirestore, private val fieldUpdateableRealisation: FieldUpdateableRealisation
) : UserRemoteRepository {

    private val logger_tag = this::class.java.simpleName
    private val documentPath = "${BuildConfig.DOCUMENT_DB_PATH}Users"

    init {
        fieldUpdateableRealisation.updateFieldDocumentPath = documentPath
    }

    override suspend fun createNewUser(userID: Int): ApiUser? {
        return try {
            val newUser = ApiUser(user_id = userID)
            firebaseFirestore.collection(documentPath).document("${newUser.user_id}").set(newUser).await()
            newUser
        } catch (e: Exception) {
            Logger.log(logger_tag, exception =  e)
            null
        }
    }

    override suspend fun getLastUserID(): Int {
        return try {
            val firebaseDocuments = firebaseFirestore.collection(documentPath).get().await()
            val lastDocumentID = firebaseDocuments?.documents?.map { it.id.toInt() }?.sorted()?.lastOrNull() ?: 0
            lastDocumentID
        } catch (e: Exception) {
            Logger.log(logger_tag, exception =  e)
            0
        }
    }

    override suspend fun getUsers(): List<ApiUser> {
        return try {
            val firebaseDocuments =  firebaseFirestore.collection(documentPath).get().await()
            firebaseDocuments?.mapNotNull { it.toObjectOrDefault(ApiUser::class.java) } ?: listOf()
        } catch (e: Exception) {
            Logger.log(logger_tag, exception = e)
            listOf()
        }
    }


    override suspend fun getUserByUserID(userID: Int): ApiUser? {
        return try {
            val firebaseData = firebaseFirestore.collection(documentPath).document("$userID").get().await()
            firebaseData?.toObjectOrDefault(ApiUser::class.java)
        } catch (e: Exception) {
            Logger.log(logger_tag, exception = e)
            null
        }
    }

    override suspend fun updateFields(
        objectID: Int,
        fieldValues: List<Pair<String, Any>>
    ): Flow<Boolean> {
        return fieldUpdateableRealisation.updateFields(objectID, fieldValues)
    }


}
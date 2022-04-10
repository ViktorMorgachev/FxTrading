package com.learning.lessons.data.repositories.userInfo

import com.google.firebase.firestore.FirebaseFirestore
import com.learning.lessons.data.BuildConfig
import com.learning.lessons.data.api.user_info.ApiUserInfo
import com.learning.lessons.data.extentions.await
import com.learning.lessons.data.extentions.toObjectOrDefault
import com.learning.lessons.data.repositories.FieldUpdateableRealisation
import com.learning.lessons.data.repositories.FieldsUpdateable
import com.learning.lessons.utils.utils.Logger
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserInfoRemoteRepositoryImpl @Inject constructor(private val firebaseFirestore: FirebaseFirestore, private val fieldUpdateableRealisation: FieldUpdateableRealisation) :
    UserInfoRemoteRepository {

    private val logger_tag = this::class.java.simpleName
    private val documentPath = "${BuildConfig.DOCUMENT_DB_PATH}UsersInfo"

    init {
        fieldUpdateableRealisation.updateFieldDocumentPath = documentPath
    }

    override suspend fun getUserInfo(userID: Int): ApiUserInfo? {
        return try {
            val firebaseDocument =
                firebaseFirestore.collection(documentPath).document("$userID").get()
                    .await()
            firebaseDocument?.toObjectOrDefault(ApiUserInfo::class.java)
        } catch (e: Exception) {
            Logger.log(logger_tag, exception = e)
            null
        }
    }

    override suspend fun getUsersInfo(): List<ApiUserInfo?> {
        return try {
            val firebaseDocuments =
                firebaseFirestore.collection("$documentPath").get().await()
            firebaseDocuments?.mapNotNull { it.toObjectOrDefault(ApiUserInfo::class.java) }
                ?: listOf()
        } catch (e: Exception) {
            Logger.log(logger_tag, exception = e)
            listOf<ApiUserInfo>()
        }
    }

    override suspend fun createUserInfo(userID: Int, deviceID: String): ApiUserInfo? {
        return try {
            val newUserInfo = ApiUserInfo(user_id = userID, devices_ids = listOf(deviceID))
            firebaseFirestore.collection(documentPath).document("$userID")
                .set(newUserInfo).await()
            newUserInfo
        } catch (e: Exception) {
            Logger.log(logger_tag, exception = e)
            null
        }
    }

    override suspend fun getUserIDByDeviceID(deviceID: String): Int? {
        return getUsersInfo().firstOrNull { it?.devices_ids?.contains(deviceID) == true }?.user_id
    }

    override suspend fun updateFields(
        objectID: Int,
        fieldValues: List<Pair<String, Any>>
    ): Flow<Boolean> {
        return fieldUpdateableRealisation.updateFields(objectID, fieldValues)
    }
}

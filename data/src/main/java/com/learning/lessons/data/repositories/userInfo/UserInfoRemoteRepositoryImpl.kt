package com.learning.lessons.data.repositories.userInfo

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.learning.lessons.data.BuildConfig
import com.learning.lessons.data.api.user_info.ApiUserInfo
import com.learning.lessons.data.extentions.await
import com.learning.lessons.data.extentions.containsAll
import com.learning.lessons.data.extentions.toObjectOrDefault
import com.learning.lessons.utils.utils.Logger
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserInfoRemoteRepositoryImpl @Inject constructor(private val firebaseFirestore: FirebaseFirestore): UserInfoRemoteRepository {

    private val logger_tag = this::class.java.simpleName
    private val documentPath = BuildConfig.DOCUMENT_DB_PATH

    override suspend fun updateUserInfoFields(
        userID: Int,
        fieldValues: List<Pair<String, Any>>
    ) =  flow<Boolean> {
        try {
            val firebaseDocumentRef = firebaseFirestore.collection("${documentPath}UsersInfo").document("$userID")
            fieldValues.forEach { fieldValue->
               firebaseDocumentRef.update(mapOf(fieldValue.first to fieldValue.second)).await()
            }
           val firebaseDocument = firebaseFirestore.collection("${documentPath}UsersInfo").document("$userID").get().await()
            if (firebaseDocument?.containsAll(fieldValues) == true){
                emit(true)
            } else emit(false)

        } catch (e: Exception) {
            Logger.log(logger_tag, exception = e)
           emit(false)
        }
    }

    override suspend fun getUserInfo(userID: Int): ApiUserInfo? {
        return try {
            val firebaseDocument = firebaseFirestore.collection("${documentPath}UsersInfo").document("$userID").get().await()
            firebaseDocument?.toObjectOrDefault(ApiUserInfo::class.java)
        } catch (e: Exception) {
            Logger.log(logger_tag, exception = e)
            null
        }
    }

    override suspend fun getUsersInfo(): List<ApiUserInfo?> {
        return try {
            val firebaseDocuments = firebaseFirestore.collection("${documentPath}UsersInfo").get().await()
            firebaseDocuments?.mapNotNull { it.toObjectOrDefault(ApiUserInfo::class.java) } ?: listOf()
        } catch (e: Exception) {
            Logger.log(logger_tag, exception = e)
            listOf<ApiUserInfo>()
        }
    }

    override suspend fun createUserInfo(userID: Int, deviceID: String): ApiUserInfo? {
        return try {
            val newUserInfo = ApiUserInfo(user_id = userID, devices_ids = listOf(deviceID))
            firebaseFirestore.collection("${documentPath}UsersInfo").document("$userID").set(newUserInfo).await()
            newUserInfo
        } catch (e: Exception) {
            Logger.log(logger_tag, exception = e)
            null
        }
    }

    override suspend fun getUserIDByDeviceID(deviceID: String): Int? {
      return getUsersInfo().firstOrNull{ it?.devices_ids?.contains(deviceID) == true }?.user_id
    }
}

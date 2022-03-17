package com.fx_trading.lessons.data.repositories.user

import com.fx_trading.lessons.data.BuildConfig
import com.fx_trading.lessons.data.api.user.ApiUser
import com.fx_trading.lessons.data.api.user.mock.MockData.Companion.defaultUser
import com.fx_trading.lessons.data.api.user_info.ApiUserInfo
import com.fx_trading.lessons.data.extentions.await
import com.fx_trading.lessons.data.extentions.getField
import com.fx_trading.lessons.data.repositories.question.documentPath
import com.fx_trading.lessons.utils.utils.Logger
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject


val pathPreferences = BuildConfig.DOCUMENT_DB_PATH

class UserRemoteRepositoryImpl @Inject constructor(
    var firebaseDatabase: FirebaseDatabase,
    val firebaseFirestore: FirebaseFirestore
) : UserRemoteRepository {

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun checkUserByDeviceIDInDatabase(deviceID: String): Boolean {

        return false
    }

    override suspend fun updateUserData(user: ApiUser): Boolean {
        try {
            val fieldToUpdate = HashMap<String, Any?>()
            fieldToUpdate.put("rank", user.rank)
            val firebaseData =
                firebaseFirestore.collection("${documentPath}Users").document("${user.user_id}")
                    .update(fieldToUpdate)
            Logger.log("UserRemoteRepository", "updateUserData $firebaseData, user $user")
            return true
        } catch (e: Exception) {
            Logger.log("UserRemoteRepository", exception = e)
            return false
        }
    }

    override suspend fun getUserIDByDeviceID(deviceID: String): Long? {
        try {
            val myRef: DatabaseReference = firebaseDatabase.getReference("${pathPreferences}UsersDeviceID")
            val result = myRef.get().await()
            result?.value?.let { value ->
                val data = value as ArrayList<HashMap<String, Any?>>
                data.forEach {
                    if (it.getField<String>("deviceID", "") == deviceID) {
                        return it.getField<Long>("userID")
                    }
                }
                return null
            }
            return null
        } catch (e: Exception) {
            Logger.log("UserRemoteRepository", exception = e)
            return null
        }
    }

    override suspend fun createNewUser(): ApiUser? {
        try {
            val firebaseDocuments =
                firebaseFirestore.collection("${documentPath}Users").get().await()
            if (firebaseDocuments != null && !firebaseDocuments.isEmpty) {
                val lastDocumentID =
                    firebaseDocuments.documents.map { it.id.toInt() }.sorted().lastOrNull()
                        ?.toLong() ?: -1
                val newUserID = lastDocumentID + 1
                val newUser = defaultUser.copy(user_id = newUserID)
                val hashMap = HashMap<String, Any?>()

                hashMap.put("date_created", newUser.date_created)
                hashMap.put("date_logined", newUser.date_logined)
                hashMap.put("user_id", newUser.user_id)

                val resulToSet = firebaseFirestore.collection("${documentPath}Users")
                    .document("${newUser.user_id}").set(hashMap).await()
                return newUser
            } else {
                if (firebaseDocuments != null && firebaseDocuments.isEmpty) {
                    val hashMap = HashMap<String, Any?>()
                    val newUser = defaultUser.copy(user_id = 1)
                    hashMap.put("date_created", newUser.date_created)
                    hashMap.put("date_logined", newUser.date_logined)
                    hashMap.put("user_id", newUser.user_id)
                    val resulToSet = firebaseFirestore.collection("${documentPath}Users")
                        .document("${newUser.user_id}").set(hashMap).await()
                    return newUser
                }
            }
            return null
        } catch (e: Exception) {
            return null
        }
    }

    override suspend fun getUserByUserID(userID: Long): ApiUser? {
        try {
            val firebaseData =
                firebaseFirestore.collection("${documentPath}Users").document("$userID").get().await()
            if (firebaseData == null || firebaseData.data == null) {
                Logger.log("QuestionRemoteRepository", "Error getting documents.")
                return null
            } else {
                return firebaseData.toObject(ApiUser::class.java)
            }
            return null
        } catch (e: Exception) {
            Logger.log("UserRemoteRepository", exception = e)
            return null
        }
    }

    override suspend fun saveResultToTesting(
        userID: Long,
        quizGroupID: Int,
        status: Int
    ): Boolean {
        try {
            // Сначала получить документ по ИД если не найден то создать новый
            val firebaseDocument = firebaseFirestore.collection("${documentPath}UsersInfo").document("$userID").get().await()
            if (firebaseDocument != null && !firebaseDocument.exists()) {
                val dataToDocument = HashMap<String, Any?>()
                val quizResultInfo = HashMap<String, Any?>()

                quizResultInfo.put("quiz_group_id", quizGroupID)
                quizResultInfo.put("status", "Пройден")

                val arrayToDocument = arrayListOf<HashMap<String, Any?>>()
                arrayToDocument.add(quizResultInfo)

                dataToDocument.put("user_id", userID)
                dataToDocument.put("quiz_results", arrayToDocument)
                firebaseFirestore.collection("${documentPath}UsersInfo").document("$userID").set(dataToDocument)
                return true
            } else {
                if (firebaseDocument != null && firebaseDocument.exists()) {
                    val dataFromDocument = firebaseDocument.data as HashMap<String, Any?>
                    val fieldFromDocument =  dataFromDocument.getField<ArrayList<HashMap<String, Any?>>>("quiz_results", arrayListOf())

                    fieldFromDocument.forEach {
                        val quizGroupIDFromFirebase = it.getField<Long>("quiz_group_id", 0).toInt()
                        if (quizGroupIDFromFirebase == quizGroupID) return true
                    }

                    val quizResultInfo = HashMap<String, Any?>()

                    quizResultInfo.put("quiz_group_id", quizGroupID)
                    quizResultInfo.put("status", "Пройден")

                    val arrayToDocument = arrayListOf<HashMap<String, Any?>>()
                    arrayToDocument.add(quizResultInfo)
                    dataFromDocument.put("quiz_results", arrayToDocument)
                    val firebaseData = firebaseFirestore.collection("${documentPath}UsersInfo").document("$userID").update(dataFromDocument)
                    return true
                }
            }
            return false
        } catch (e: Exception) {
            Logger.log("UserRemoteRepository", exception = e)
            return false
        }
    }

    override suspend fun saveDeviceAndUserID(userId: Long, deviceID: String): Boolean {
        try {
            val myRef: DatabaseReference = firebaseDatabase.getReference("${pathPreferences}UsersDeviceID")
            val dataForSaving = ArrayList<HashMap<String, Any?>>()
            val hashMap = HashMap<String, Any?>()
            hashMap.put("deviceID", deviceID)
            hashMap.put("userID", userId)
            dataForSaving.add(hashMap)
            myRef.setValue(dataForSaving)
            return true
        } catch (e: Exception) {
            Logger.log("UserRemoteRepository", exception = e)
            return false
        }
    }

    override suspend fun getUserInfoByUserID(userId: Long): ApiUserInfo? {
        try {
            val firebaseDocument = firebaseFirestore.collection("${documentPath}UsersInfo").document("$userId").get().await()
            if (firebaseDocument != null  && firebaseDocument.data != null){
                val userInfo = firebaseDocument.toObject(ApiUserInfo::class.java)
                return userInfo
            } else
            return null
        } catch (e: Exception) {
            Logger.log("UserRemoteRepository", exception = e)
            return null
        }
    }

    override suspend fun updateUserInfo(userInfo: ApiUserInfo): Boolean {
        try {
            firebaseFirestore.collection("${com.fx_trading.lessons.data.repositories.lessons.documentPath}Lessons").document("${userInfo.user_id}").set(userInfo).await()
            return true
        } catch (e: Exception) {
            Logger.log("LessonsRemoteRepository", "Error getting documents.", exception = e)
            return false
        }
    }
}
package com.learning.lessons.data.repositories.user

import com.learning.lessons.data.BuildConfig
import com.learning.lessons.data.api.user.ApiUser
import com.learning.lessons.data.api.user.mock.MockData.Companion.defaultUser
import com.learning.lessons.data.api.user_info.ApiUserInfo
import com.learning.lessons.data.extentions.await
import com.learning.lessons.data.extentions.getField
import com.learning.lessons.utils.utils.Logger
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject



class UserRemoteRepositoryImpl @Inject constructor(
    var firebaseDatabase: FirebaseDatabase,
    val firebaseFirestore: FirebaseFirestore
) : UserRemoteRepository {

    private val documentPath = BuildConfig.DOCUMENT_DB_PATH

    override suspend fun updateUserData(user: ApiUser): Boolean {
        try {
            firebaseFirestore.collection("${documentPath}Users").document("${user.user_id}").set(user).await()
            Logger.log("UserRemoteRepository", "user $user")
            return true
        } catch (e: Exception) {
            Logger.log("UserRemoteRepository", exception = e)
            return false
        }
    }

    override suspend fun getUserIDByDeviceID(deviceID: String): Int? {
        try {
            val myRef: DatabaseReference = firebaseDatabase.getReference("${documentPath}UsersDeviceID")
            val result = myRef.get().await()
            result?.value?.let { value ->
                val data = value as ArrayList<HashMap<String, Any?>>
                data.forEach {
                    if (it.getField<String>("deviceID", "") == deviceID) {
                        return it.getField<Long>("userID") as Int
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
            val firebaseDocuments = firebaseFirestore.collection("${documentPath}Users").get().await()
            if (firebaseDocuments != null && !firebaseDocuments.isEmpty) {
                val lastDocumentID = firebaseDocuments.documents.map { it.id.toInt() }.sorted().lastOrNull()?.toLong() ?: -1
                val newUserID = lastDocumentID + 1
                val newUser = defaultUser.copy(user_id = newUserID.toInt())
                val hashMap = HashMap<String, Any?>()

                hashMap.put("date_created", newUser.date_created)
                hashMap.put("date_logined", newUser.date_logined)
                hashMap.put("user_id", newUser.user_id)
                firebaseFirestore.collection("${documentPath}Users").document("${newUser.user_id}").set(hashMap).await()
                return newUser
            } else {
                if (firebaseDocuments != null && firebaseDocuments.isEmpty) {
                    val hashMap = HashMap<String, Any?>()
                    val newUser = defaultUser.copy(user_id = 1)
                    hashMap.put("date_created", newUser.date_created)
                    hashMap.put("date_logined", newUser.date_logined)
                    hashMap.put("user_id", newUser.user_id)
                    firebaseFirestore.collection("${documentPath}Users").document("${newUser.user_id}").set(hashMap).await()
                    return newUser
                }
            }
            return null
        } catch (e: Exception) {
            return null
        }
    }

    override suspend fun getUserByUserID(userID: Int): ApiUser? {
        try {
            val firebaseData = firebaseFirestore.collection("${documentPath}Users").document("$userID").get().await()
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

    override suspend fun saveDeviceAndUserID(userId: Int, deviceID: String): Boolean {
        try {
            val dataForSaving = ArrayList<HashMap<String, Any?>>()
            val myRef: DatabaseReference = firebaseDatabase.getReference("${documentPath}UsersDeviceID")
            val dataFromMyRef = firebaseDatabase.getReference("${documentPath}UsersDeviceID").get().await()
            dataFromMyRef?.value?.let { value ->
                val data = value as ArrayList<HashMap<String, Any?>>
                data.forEach {
                    dataForSaving.add(it)
                }
            }
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

    override suspend fun getUserInfoByUserID(userId: Int): ApiUserInfo? {
        try {
            val firebaseDocument = firebaseFirestore.collection("${documentPath}UsersInfo").document("$userId").get().await()
            if (firebaseDocument != null && firebaseDocument.data != null){
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
            firebaseFirestore.collection("${documentPath}UsersInfo").document("${userInfo.user_id}").set(userInfo).await()
            return true
        } catch (e: Exception) {
            Logger.log("LessonsRemoteRepository", "Error getting documents.", exception = e)
            return false
        }
    }

    override suspend fun createNewUserInfo(userId: Int): ApiUserInfo? {
        try {
            val firebaseDocuments = firebaseFirestore.collection("${documentPath}UsersInfo").get().await()
            if (firebaseDocuments != null && !firebaseDocuments.isEmpty) {
                val lastDocumentID = firebaseDocuments.documents.map { it.id.toInt() }.sorted().lastOrNull()?.toLong() ?: -1
                val newDocumentID = lastDocumentID + 1
                val newUserInfo = ApiUserInfo(user_id = userId.toInt())
                firebaseFirestore.collection("${documentPath}UsersInfo").document("$newDocumentID").set(newUserInfo).await()
                return newUserInfo
            } else
            return null
        } catch (e: Exception) {
            return null
        }
    }
}
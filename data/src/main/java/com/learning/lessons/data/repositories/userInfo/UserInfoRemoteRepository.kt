package com.learning.lessons.data.repositories.userInfo

import com.learning.lessons.data.api.user_info.ApiUserInfo
import kotlinx.coroutines.flow.Flow

interface UserInfoRemoteRepository {
    suspend fun updateUserInfoFields(userID: Int, fieldValues: List<Pair<String, Any>>): Flow<Boolean>
    suspend fun getUserInfo(userID: Int): ApiUserInfo?
    suspend fun getUsersInfo(): List<ApiUserInfo?>
    suspend fun createUserInfo(userID: Int, deviceID: String): ApiUserInfo?
    suspend fun getUserIDByDeviceID(deviceID: String): Int?
}
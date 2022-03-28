package com.learning.lessons.data.repositories.userInfo

import com.learning.lessons.data.api.user_info.ApiUserInfo

interface UserInfoRemoteRepository {
    suspend fun updateUserInfoFields(userID: Int, fieldValue: List<Pair<String, Any>>): Boolean
    suspend fun getUserInfo(userID: Int): ApiUserInfo?
    suspend fun getUsersInfo(): List<ApiUserInfo?>
    suspend fun createUserInfo(userID: Int, deviceID: String): ApiUserInfo?
    suspend fun getUserIDByDeviceID(deviceID: String): Int?
}
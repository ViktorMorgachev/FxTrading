package com.learning.lessons.data.repositories.user

import com.learning.lessons.data.api.user.ApiUser
import com.learning.lessons.data.api.user_info.ApiUserInfo

interface UserRemoteRepository {
    suspend fun updateUserData(user: ApiUser): Boolean
    suspend fun getUserIDByDeviceID(deviceID: String): Int?
    suspend fun createNewUser(): ApiUser?
    suspend fun getUserByUserID(userID: Int): ApiUser?
    suspend fun saveDeviceAndUserID(userId: Int, deviceID: String): Boolean
    suspend fun getUserInfoByUserID(userId: Int): ApiUserInfo?
    suspend fun updateUserInfo(userInfo: ApiUserInfo): Boolean
    suspend fun createNewUserInfo(userId: Int): ApiUserInfo?
}
package com.fx_trading.lessons.data.repositories.user

import com.fx_trading.lessons.data.api.user.ApiUser
import com.fx_trading.lessons.data.api.user_info.ApiUserInfo
import kotlinx.coroutines.flow.Flow

interface UserRemoteRepository {
    suspend fun updateUserData(user: ApiUser): Boolean
    suspend fun getUserIDByDeviceID(deviceID: String): Long?
    suspend fun createNewUser(): ApiUser?
    suspend fun getUserByUserID(userID: Long): ApiUser?
    suspend fun saveResultToTesting(userID: Long, quizGroupID: Int, status: Int): Boolean
    suspend fun saveDeviceAndUserID(userId: Long, deviceID: String): Boolean
    suspend fun getUserInfoByUserID(userId: Long): ApiUserInfo?
    suspend fun updateUserInfo(userInfo: ApiUserInfo): Boolean
}
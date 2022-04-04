package com.learning.lessons.data.repositories.userInfo

import com.learning.lessons.data.api.user_info.ApiUserInfo
import com.learning.lessons.data.repositories.FieldsUpdateable
import kotlinx.coroutines.flow.Flow

interface UserInfoRemoteRepository: FieldsUpdateable {
    suspend fun getUserInfo(userID: Int): ApiUserInfo?
    suspend fun getUsersInfo(): List<ApiUserInfo?>
    suspend fun createUserInfo(userID: Int, deviceID: String): ApiUserInfo?
    suspend fun getUserIDByDeviceID(deviceID: String): Int?
}
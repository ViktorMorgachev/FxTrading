package com.fx_trading.lessons.data.repositories.user

import com.fx_trading.lessons.data.api.user.ApiUser
import com.fx_trading.lessons.domain.entities.user.User

interface UserRemoteRepository {
    suspend fun fetchUserByUserID(userID: String): ApiUser
    suspend fun checkUserByDeviceIDInDatabase(deviceID: String): Boolean
    suspend fun updateUserData(user: User)
    suspend fun getUserIDByDeviceID(deviceID: String): String?
}
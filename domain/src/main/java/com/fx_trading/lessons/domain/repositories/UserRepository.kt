package com.fx_trading.lessons.domain.repositories

import com.fx_trading.lessons.domain.entities.user.User

interface UserRepository {
   suspend fun fetchUserByUserID(userID: String): User
   suspend fun updateUserData(user: User)
   suspend fun getUserIDByDeviceID(deviceID: String) : String?
   suspend fun checkUserByDeviceIDInDatabase(): Boolean

}
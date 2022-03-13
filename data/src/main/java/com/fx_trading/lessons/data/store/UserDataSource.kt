package com.fx_trading.lessons.data.store

import com.fx_trading.lessons.data.api.user.ApiUser
import com.fx_trading.lessons.data.repositories.user.UserRemoteRepository
import com.fx_trading.lessons.domain.entities.user.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataSource @Inject constructor(private val userRemoteRepository: UserRemoteRepository) :
    UserRemoteRepository {

    override suspend fun fetchUserByUserID(userID: String): ApiUser {
        TODO("Not yet implemented")
    }

    override suspend fun checkUserByDeviceIDInDatabase(deviceID: String): Boolean {
        return userRemoteRepository.checkUserByDeviceIDInDatabase(deviceID)
    }

    override suspend fun updateUserData(user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun getUserIDByDeviceID(deviceID: String): String? {
        val createNewUser = userRemoteRepository.checkUserByDeviceIDInDatabase(deviceID)
        return null
    }

}
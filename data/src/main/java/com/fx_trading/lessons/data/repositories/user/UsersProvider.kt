package com.fx_trading.lessons.data.repositories.user

import com.fx_trading.lessons.data.mappers.toUser
import com.fx_trading.lessons.data.pseudoDeviceID
import com.fx_trading.lessons.data.store.UserDataSource
import com.fx_trading.lessons.domain.entities.user.User
import com.fx_trading.lessons.domain.repositories.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersProvider @Inject constructor(private val userDataSource: UserDataSource): UserRepository {

    override suspend fun fetchUserByUserID(userID: String): User {
       return userDataSource.fetchUserByUserID(userID).toUser()
    }

    override suspend fun updateUserData(user: User) {
        TODO("Not yet implemented")
    }



    override suspend fun getUserIDByDeviceID(deviceID: String): String? {
        return userDataSource.getUserIDByDeviceID(deviceID)
    }

    override suspend fun checkUserByDeviceIDInDatabase(): Boolean {
        return userDataSource.checkUserByDeviceIDInDatabase(deviceID = pseudoDeviceID)
    }

}
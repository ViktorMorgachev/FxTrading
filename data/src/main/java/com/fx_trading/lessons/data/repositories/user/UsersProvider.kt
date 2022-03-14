package com.fx_trading.lessons.data.repositories.user

import com.fx_trading.lessons.data.pseudoDeviceID
import com.fx_trading.lessons.data.store.UserDataSource
import com.fx_trading.lessons.domain.entities.user.User
import com.fx_trading.lessons.domain.repositories.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersProvider @Inject constructor(private val userDataSource: UserDataSource): UserRepository {

    override suspend fun getUserByUserID(userID: Long): User? {
       return userDataSource.getUserByUserID(userID)
    }

    override suspend fun updateUserData(user: User): Boolean {
      return userDataSource.updateUserData(user)
    }

    override suspend fun getUserIDByDeviceID(): Long? {
        return userDataSource.getUserIDByDeviceID(deviceID = pseudoDeviceID)
    }

    override suspend fun checkUserByDeviceIDInDatabase(): Boolean {
        return userDataSource.checkUserByDeviceIDInDatabase(deviceID = pseudoDeviceID)
    }

    override suspend fun createNewUser(): User? {
      return userDataSource.createNewUser()
    }

    override suspend fun saveResultTesting(userID: Long, quizGroupID: Int, status: Int): Boolean {
       return userDataSource.saveResultToTesting(userID, quizGroupID, status)
    }

    override suspend fun saveDeviceIDAndUserID(userId: Long): Boolean {
        return userDataSource.saveDeviceAndUserID(userId, pseudoDeviceID)
    }

}
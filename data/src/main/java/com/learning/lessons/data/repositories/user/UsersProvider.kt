package com.learning.lessons.data.repositories.user

import com.learning.lessons.data.pseudoDeviceID
import com.learning.lessons.data.store.UserDataSource
import com.learning.lessons.domain.entities.user.User
import com.learning.lessons.domain.repositories.UserRepository
import kotlinx.coroutines.Deferred
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersProvider @Inject constructor(private val userDataSource: UserDataSource): UserRepository {

    override suspend fun getUserByUserID(userID: Int): User? {
       return userDataSource.getUserByUserID(userID)
    }

    override suspend fun updateUserField(
        userID: Int,
        fieldValue: List<Pair<String, Any>>
    ): Deferred<Boolean> {
        return userDataSource.updateUserField(userID, fieldValue)
    }

    override suspend fun createNewUser(userID: Int): User? {
        TODO("Not yet implemented")
    }

    override suspend fun getLastUserID(): Int {
        TODO("Not yet implemented")
    }


}
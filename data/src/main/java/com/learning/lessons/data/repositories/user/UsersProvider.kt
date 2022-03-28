package com.learning.lessons.data.repositories.user

import com.learning.lessons.data.store.UserDataSource
import com.learning.lessons.domain.entities.user.User
import kotlinx.coroutines.Deferred
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersProvider @Inject constructor(private val userDataSource: UserDataSource) {

    suspend fun getUserByUserID(userID: Int): User? {
       return userDataSource.getUserByUserID(userID)
    }

    suspend fun getUsers(): List<User> {
        return userDataSource.getUsers()
    }

    suspend fun updateUserField(
        userID: Int,
        fieldValue: List<Pair<String, Any>>
    ): Deferred<Boolean> {
        return userDataSource.updateUserField(userID, fieldValue)
    }

    suspend fun createNewUser(userID: Int): User? {
     return userDataSource.createNewUser(userID)
    }

    suspend fun getLastUserID(): Int {
       return userDataSource.getLastUserID()
    }


}
package com.learning.lessons.data.repositories.user

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

    override suspend fun getUsers(): List<User> {
        return userDataSource.getUsers()
    }

    override suspend fun createNewUser(userID: Int): User? {
     return userDataSource.createNewUser(userID)
    }

    override suspend fun getLastUserID(): Int {
       return userDataSource.getLastUserID()
    }

    override suspend fun updateFields(
        objectID: Int,
        fieldValue: List<Pair<String, Any>>
    ): Deferred<Boolean> {
        return userDataSource.updateUserField(objectID, fieldValue)
    }


}
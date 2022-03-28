package com.learning.lessons.data.store

import com.google.firebase.inject.Deferred
import com.learning.lessons.data.mappers.toApiUser
import com.learning.lessons.data.mappers.toApiUserInfo
import com.learning.lessons.data.mappers.toUser
import com.learning.lessons.data.mappers.toUserInfo
import com.learning.lessons.data.pseudoDeviceID
import com.learning.lessons.data.repositories.user.UserRemoteRepository
import com.learning.lessons.domain.entities.user.User
import com.learning.lessons.domain.entities.users_info.UserInfo
import com.learning.lessons.domain.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserDataSource @Inject constructor(private val userRemoteRepository: UserRemoteRepository): UserRepository {

    override suspend fun getUserByUserID(userID: Int): User? {
       return userRemoteRepository.getUserByUserID(userID)?.toUser()
    }

    override suspend fun getUsers(): List<User> {
        return userRemoteRepository.getUsers()
    }

    override suspend fun updateUserField(
        userID: Int,
        fieldValue: List<Pair<String, Any>>
    ): kotlinx.coroutines.Deferred<Boolean> = withContext(Dispatchers.IO){
        return@withContext async { userRemoteRepository.updateUserField(userID, fieldValue) }
    }

    override suspend fun createNewUser(userID: Int): User? {
       return userRemoteRepository.createNewUser(userID = userID)?.toUser()
    }

    override suspend fun getLastUserID(): Int {
      return userRemoteRepository.getLastUserID()
    }

}
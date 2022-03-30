package com.learning.lessons.data.store

import com.learning.lessons.data.mappers.toUser
import com.learning.lessons.data.repositories.user.UserRemoteRepository
import com.learning.lessons.domain.entities.user.User
import com.learning.lessons.domain.entities.users_info.UserInfo
import com.learning.lessons.domain.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserDataSource @Inject constructor(private val userRemoteRepository: UserRemoteRepository) {

     suspend fun getUserByUserID(userID: Int): User? {
       return userRemoteRepository.getUserByUserID(userID)?.toUser()
    }

     suspend fun getUsers(): List<User> {
        return userRemoteRepository.getUsers()
    }

     suspend fun updateUserField(
        userID: Int,
        fieldValue: List<Pair<String, Any>>
    ): kotlinx.coroutines.Deferred<Boolean> = withContext(Dispatchers.IO){
        return@withContext async { userRemoteRepository.updateUserField(userID, fieldValue).last() }
    }

     suspend fun createNewUser(userID: Int): User? {
       return userRemoteRepository.createNewUser(userID = userID)?.toUser()
    }

     suspend fun getLastUserID(): Int {
      return userRemoteRepository.getLastUserID()
    }

}
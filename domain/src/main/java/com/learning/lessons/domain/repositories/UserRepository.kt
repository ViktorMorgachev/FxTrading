package com.learning.lessons.domain.repositories

import com.learning.lessons.domain.entities.user.User
import com.learning.lessons.domain.entities.users_info.UserInfo
import kotlinx.coroutines.Deferred

interface UserRepository {
    suspend fun getUserByUserID(userID: Int): User?
    suspend fun getUsers(): List<User>
    suspend fun updateUserField(userID: Int, fieldValue: List<Pair<String, Any>>): Deferred<Boolean>
    suspend fun createNewUser(userID: Int): User?
    suspend fun getLastUserID(): Int
}
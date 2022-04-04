package com.learning.lessons.domain.repositories

import com.learning.lessons.domain.entities.user.User

interface UserRepository: FieldsUpdateable {
    suspend fun getUserByUserID(userID: Int): User?
    suspend fun getUsers(): List<User>
    suspend fun createNewUser(userID: Int): User?
    suspend fun getLastUserID(): Int
}
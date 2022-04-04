package com.learning.lessons.data.repositories.user

import com.learning.lessons.data.api.user.ApiUser
import com.learning.lessons.data.api.user_info.ApiUserInfo
import com.learning.lessons.data.repositories.FieldsUpdateable
import com.learning.lessons.domain.entities.user.User
import kotlinx.coroutines.flow.Flow

interface UserRemoteRepository: FieldsUpdateable {
    suspend fun createNewUser(userID: Int): ApiUser?
    suspend fun getLastUserID(): Int
    suspend fun getUsers(): List<ApiUser>
    suspend fun getUserByUserID(userID: Int): ApiUser?
}
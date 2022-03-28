package com.learning.lessons.domain.repositories

import com.learning.lessons.domain.entities.users_info.UserInfo
import kotlinx.coroutines.Deferred

interface UserInfoRepository {
    suspend fun getUserInfo(userID: Int): UserInfo?
    suspend fun updateUserInfoFields(userID: Int, fieldValue: List<Pair<String, Any>>): Deferred<Boolean>
    suspend fun createNewUserInfo(userID: Int): UserInfo?
    suspend fun getUserIDByDeviceID(): Int?
}
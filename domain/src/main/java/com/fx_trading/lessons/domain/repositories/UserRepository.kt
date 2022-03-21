package com.fx_trading.lessons.domain.repositories

import com.fx_trading.lessons.domain.entities.user.User
import com.fx_trading.lessons.domain.entities.users_info.UserInfo

interface UserRepository {
    suspend fun getUserByUserID(userID: Long): User?
    suspend fun updateUserData(user: User): Boolean
    suspend fun getUserIDByDeviceID(): Long?
    suspend fun checkUserByDeviceIDInDatabase(): Boolean
    suspend fun createNewUser(): User?
    suspend fun saveResultTesting(userID: Long, quizGroupID: Int, status: Int): Boolean
    suspend fun saveDeviceIDAndUserID(userId: Long): Boolean
    suspend fun getUserInfoByUserID(userID: Long): UserInfo?
    suspend fun updateUserInfoLessonLike(lessonID: Long, userID: Long): Boolean
    suspend fun updateUserInfoLessonDisLike(lessonID: Long, userID: Long): Boolean
}
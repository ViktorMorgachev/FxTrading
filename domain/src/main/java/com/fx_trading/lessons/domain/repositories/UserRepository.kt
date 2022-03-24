package com.fx_trading.lessons.domain.repositories

import com.fx_trading.lessons.domain.entities.user.User
import com.fx_trading.lessons.domain.entities.users_info.UserInfo

interface UserRepository {
    suspend fun getUserByUserID(userID: Long): User?
    suspend fun updateUserData(user: User): Boolean
    suspend fun getUserIDByDeviceID(): Long?
    suspend fun createNewUser(): User?
    suspend fun saveLessonPassed(lessonID: Long, userID: Long): Boolean
    suspend fun firstSaveQuestionPassed(questionID: Long, userId: Long): Boolean
    suspend fun saveQuestionPassed(questionID: Long, userId: Long): Boolean
    suspend fun saveDeviceIDAndUserID(userId: Long): Boolean
    suspend fun getUserInfoByUserID(userID: Long): UserInfo?
    suspend fun updateUserInfoLessonLike(lessonID: Long, userID: Long): Boolean
    suspend fun updateUserInfoLessonDisLike(lessonID: Long, userID: Long): Boolean
}
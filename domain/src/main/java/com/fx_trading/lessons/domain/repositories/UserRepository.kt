package com.fx_trading.lessons.domain.repositories

import com.fx_trading.lessons.domain.entities.user.User
import com.fx_trading.lessons.domain.entities.users_info.UserInfo

interface UserRepository {
    suspend fun getUserByUserID(userID: Int): User?
    suspend fun updateUserData(user: User): Boolean
    suspend fun getUserIDByDeviceID(): Int?
    suspend fun createNewUser(): User?
    suspend fun updateUserInfo(userInfo: UserInfo): Boolean
    suspend fun saveLessonPassed(lessonID: Int, userID: Int): Boolean
    suspend fun firstSaveQuestionPassed(questionID: Int, userId: Int): Boolean
    suspend fun saveQuestionPassed(questionID: Int, userId: Int): Boolean
    suspend fun saveDeviceIDAndUserID(userId: Int): Boolean
    suspend fun getUserInfoByUserID(userID: Int): UserInfo?
    suspend fun updateUserInfoLessonLike(lessonID: Int, userID: Int): Boolean
    suspend fun updateUserInfoLessonDisLike(lessonID: Int, userID: Int): Boolean
}
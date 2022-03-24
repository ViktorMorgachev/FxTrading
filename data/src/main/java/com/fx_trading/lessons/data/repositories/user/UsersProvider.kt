package com.fx_trading.lessons.data.repositories.user

import com.fx_trading.lessons.data.pseudoDeviceID
import com.fx_trading.lessons.data.store.UserDataSource
import com.fx_trading.lessons.domain.entities.user.User
import com.fx_trading.lessons.domain.entities.users_info.UserInfo
import com.fx_trading.lessons.domain.repositories.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersProvider @Inject constructor(private val userDataSource: UserDataSource): UserRepository {

    override suspend fun getUserByUserID(userID: Long): User? {
       return userDataSource.getUserByUserID(userID)
    }

    override suspend fun updateUserData(user: User): Boolean {
      return userDataSource.updateUserData(user)
    }

    override suspend fun getUserIDByDeviceID(): Long? {
        return userDataSource.getUserIDByDeviceID(deviceID = pseudoDeviceID)
    }

    override suspend fun createNewUser(): User? {
      return userDataSource.createNewUser()
    }

    override suspend fun saveLessonPassed(lessonID: Long, userID: Long): Boolean {
        return userDataSource.saveLessonPassed(lessonID, userID)
    }


    override suspend fun firstSaveQuestionPassed(questionID: Long, userId: Long): Boolean {
        return userDataSource.updateOrCreateUserInfoQuestionPassed(questionID, userId)
    }

    override suspend fun saveQuestionPassed(questionID: Long, userId: Long): Boolean {
        return userDataSource.saveQuestionPassed(questionID = questionID, userID = userId)
    }

    override suspend fun saveDeviceIDAndUserID(userId: Long): Boolean {
        return userDataSource.saveDeviceAndUserID(userId, pseudoDeviceID)
    }

    override suspend fun getUserInfoByUserID(userID: Long): UserInfo? {
        return userDataSource.getUserInfoByUserID(userID)
    }

    override suspend fun updateUserInfoLessonLike(lessonID: Long, userID: Long): Boolean {
      return userDataSource.updateUserInfoLessonLike(lessonID, userId = userID)
    }

    override suspend fun updateUserInfoLessonDisLike(lessonID: Long, userID: Long): Boolean {
        return userDataSource.updateUserInfoLessonDislike(lessonID, userId = userID)
    }

}
package com.learning.lessons.data.repositories.user

import com.learning.lessons.data.pseudoDeviceID
import com.learning.lessons.data.store.UserDataSource
import com.learning.lessons.domain.entities.user.User
import com.learning.lessons.domain.entities.users_info.UserInfo
import com.learning.lessons.domain.repositories.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersProvider @Inject constructor(private val userDataSource: UserDataSource): UserRepository {

    override suspend fun getUserByUserID(userID: Int): User? {
       return userDataSource.getUserByUserID(userID)
    }

    override suspend fun updateUserData(user: User): Boolean {
      return userDataSource.updateUserData(user)
    }

    override suspend fun getUserIDByDeviceID(): Int? {
        return userDataSource.getUserIDByDeviceID(deviceID = pseudoDeviceID)
    }

    override suspend fun createNewUser(): User? {
      return userDataSource.createNewUser()
    }

    override suspend fun updateUserInfo(userInfo: UserInfo): Boolean {
        return userDataSource.updateUserInfo(userInfo)
    }

    override suspend fun saveLessonPassed(lessonID: Int, userID: Int): Boolean {
        return userDataSource.saveLessonPassed(lessonID, userID)
    }


    override suspend fun firstSaveQuestionPassed(questionID: Int, userId: Int): Boolean {
        return userDataSource.updateOrCreateUserInfoQuestionPassed(questionID, userId)
    }

    override suspend fun saveQuestionPassed(questionID: Int, userId: Int): Boolean {
        return userDataSource.saveQuestionPassed(questionID = questionID, userID = userId)
    }

    override suspend fun saveDeviceIDAndUserID(userId: Int): Boolean {
        return userDataSource.saveDeviceAndUserID(userId, pseudoDeviceID)
    }

    override suspend fun getUserInfoByUserID(userID: Int): UserInfo? {
        return userDataSource.getUserInfoByUserID(userID)
    }

    override suspend fun updateUserInfoLessonLike(lessonID: Int, userID: Int): Boolean {
      return userDataSource.updateUserInfoLessonLike(lessonID, userId = userID)
    }

    override suspend fun updateUserInfoLessonDisLike(lessonID: Int, userID: Int): Boolean {
        return userDataSource.updateUserInfoLessonDislike(lessonID, userId = userID)
    }

}
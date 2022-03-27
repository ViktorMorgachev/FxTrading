package com.learning.lessons.data.store

import com.learning.lessons.data.mappers.toApiUser
import com.learning.lessons.data.mappers.toApiUserInfo
import com.learning.lessons.data.mappers.toUser
import com.learning.lessons.data.mappers.toUserInfo
import com.learning.lessons.data.repositories.user.UserRemoteRepository
import com.learning.lessons.domain.entities.user.User
import com.learning.lessons.domain.entities.users_info.UserInfo
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserDataSource @Inject constructor(private val userRemoteRepository: UserRemoteRepository) {

    suspend fun getUserIDByDeviceID(deviceID: String): Int? {
        return userRemoteRepository.getUserIDByDeviceID(deviceID)
    }

    suspend fun createNewUser(): User? {
        return userRemoteRepository.createNewUser()?.toUser()
    }

    suspend fun updateUserInfo(userInfo: UserInfo): Boolean{
        return userRemoteRepository.updateUserInfo(userInfo = userInfo.toApiUserInfo())
    }

    suspend fun updateUserData(user: User): Boolean {
        return userRemoteRepository.updateUserData(user = user.toApiUser())
    }

    suspend fun getUserByUserID(userId: Int): User? {
        return userRemoteRepository.getUserByUserID(userId)?.toUser()
    }


    suspend fun saveDeviceAndUserID(userId: Int, deviceID: String): Boolean {
        return userRemoteRepository.saveDeviceAndUserID(userId, deviceID)
    }

    suspend fun getUserInfoByUserID(userId: Int): UserInfo? {
        return userRemoteRepository.getUserInfoByUserID(userId)?.toUserInfo()
    }

    suspend fun updateUserInfoLessonLike(lessonID: Int, userId: Int): Boolean {
        val userInfo = userRemoteRepository.getUserInfoByUserID(userId)
        return if (userInfo != null){
            userRemoteRepository.updateUserInfo(userInfo.copy(likes_lessons_ids = userInfo.likes_lessons_ids.plus(lessonID)))
        } else false
    }

    suspend fun saveQuestionPassed(questionID: Int, userID: Int): Boolean {
        val userInfo = userRemoteRepository.getUserInfoByUserID(userID)
        return if (userInfo != null && !userInfo.questions_ids.contains(questionID)){
            userRemoteRepository.updateUserInfo(userInfo.copy(questions_ids = userInfo.questions_ids.plus(questionID)))
        } else false
    }

    suspend fun updateOrCreateUserInfoQuestionPassed(questionID: Int, userId: Int): Boolean{
        val userInfo = userRemoteRepository.getUserInfoByUserID(userId)
        return if (userInfo != null){
            if (!userInfo.questions_ids.contains(questionID)){
                return  userRemoteRepository.updateUserInfo(userInfo.copy(questions_ids = userInfo.questions_ids.plus(questionID)))
            }
            return false
        } else {
           val newUserInfo = userRemoteRepository.createNewUserInfo(userId)
            if (newUserInfo != null){
                if (!newUserInfo.questions_ids.contains(questionID)){
                    return  userRemoteRepository.updateUserInfo(newUserInfo.copy(questions_ids = newUserInfo.questions_ids.plus(questionID)))
                }
                return false
            }
            return false
        }
    }

    suspend fun updateUserInfoLessonDislike(lessonID: Int, userId: Int): Boolean {
        val userInfo = userRemoteRepository.getUserInfoByUserID(userId)
        return if (userInfo != null){
            userRemoteRepository.updateUserInfo(userInfo.copy(dislikes_lessons_ids = userInfo.dislikes_lessons_ids.plus(lessonID)))
        } else false
    }

    suspend fun saveLessonPassed(lessonID: Int, userID: Int): Boolean {
        val userInfo = userRemoteRepository.getUserInfoByUserID(userID)
        return if (userInfo != null && !userInfo.passed_lessons_ids.contains(lessonID)){
            userRemoteRepository.updateUserInfo(userInfo.copy(passed_lessons_ids = userInfo.passed_lessons_ids.plus(lessonID)))
        } else false
    }

}
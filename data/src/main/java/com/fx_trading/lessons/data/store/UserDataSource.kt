package com.fx_trading.lessons.data.store

import com.fx_trading.lessons.data.BuildConfig
import com.fx_trading.lessons.data.api.user_info.mock.MockData
import com.fx_trading.lessons.data.mappers.toApiUser
import com.fx_trading.lessons.data.mappers.toApiUserInfo
import com.fx_trading.lessons.data.mappers.toUser
import com.fx_trading.lessons.data.mappers.toUserInfo
import com.fx_trading.lessons.data.repositories.user.UserRemoteRepository
import com.fx_trading.lessons.domain.entities.user.User
import com.fx_trading.lessons.domain.entities.users_info.UserInfo
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserDataSource @Inject constructor(private val userRemoteRepository: UserRemoteRepository) {

    private val useMockData = BuildConfig.USE_MOCK_DATA

    companion object{
        private var userInfo: UserInfo? = null
    }

    suspend fun checkUserByDeviceIDInDatabase(deviceID: String): Boolean {
        return userRemoteRepository.checkUserByDeviceIDInDatabase(deviceID = deviceID)
    }

    suspend fun getUserIDByDeviceID(deviceID: String): Long? {
        return userRemoteRepository.getUserIDByDeviceID(deviceID)
    }

    suspend fun createNewUser(): User? {
        return userRemoteRepository.createNewUser()?.toUser()
    }

    suspend fun updateUserData(user: User): Boolean {
        return userRemoteRepository.updateUserData(user = user.toApiUser())
    }

    suspend fun getUserByUserID(userId: Long): User? {
        return userRemoteRepository.getUserByUserID(userId)?.toUser()
    }

    suspend fun saveResultToTesting(userID: Long, quizGroupID: Int, status: Int): Boolean {
        return userRemoteRepository.saveResultToTesting(userID, quizGroupID, status)
    }

    suspend fun saveDeviceAndUserID(userId: Long, deviceID: String): Boolean {
        return userRemoteRepository.saveDeviceAndUserID(userId, deviceID)
    }

    suspend fun getUserInfoByUserID(userId: Long): UserInfo? {
        if (userInfo != null) return userInfo
        if (useMockData) {
            return MockData.mockApiUserInfo.toUserInfo()
        } else {
            val data = userRemoteRepository.getUserInfoByUserID(userId)?.toUserInfo()
            data?.let {
                userInfo = it
            }
            return userInfo
        }
    }

    suspend fun updateUserInfoLessonLike(lessonID: Long): Boolean {
        if (userInfo != null){
            return userRemoteRepository.updateUserInfo(userInfo!!.toApiUserInfo())
        } else return false
    }

}
package com.learning.lessons.data.store

import com.learning.lessons.data.mappers.toUserInfo
import com.learning.lessons.data.pseudoDeviceID
import com.learning.lessons.data.repositories.userInfo.UserInfoRemoteRepository
import com.learning.lessons.domain.entities.users_info.UserInfo
import com.learning.lessons.domain.repositories.UserInfoRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserInfoDataSource @Inject constructor(private val userInfoRemoteRepository: UserInfoRemoteRepository): UserInfoRepository {

    override suspend fun getUserInfo(userID: Int): UserInfo? {
      return userInfoRemoteRepository.getUserInfo(userID)?.toUserInfo()
    }

    override suspend fun updateUserInfoFields(
        userID: Int,
        fieldValue: List<Pair<String, Any>>
    ): Deferred<Boolean> = withContext(Dispatchers.IO){
        return@withContext async {userInfoRemoteRepository.updateUserInfoFields(userID, fieldValue)  }
    }

    override suspend fun createNewUserInfo(userID: Int): UserInfo? {
       return userInfoRemoteRepository.createUserInfo(userID = userID, deviceID = pseudoDeviceID)?.toUserInfo()
    }

    override suspend fun getUserIDByDeviceID(): Int? {
        return userInfoRemoteRepository.getUserIDByDeviceID(deviceID = pseudoDeviceID)
    }

}
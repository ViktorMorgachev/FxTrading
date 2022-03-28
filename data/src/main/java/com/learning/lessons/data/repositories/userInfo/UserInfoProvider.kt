package com.learning.lessons.data.repositories.userInfo

import com.learning.lessons.data.store.UserInfoDataSource
import com.learning.lessons.domain.entities.users_info.UserInfo
import com.learning.lessons.domain.repositories.UserInfoRepository
import kotlinx.coroutines.Deferred
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserInfoProvider @Inject constructor(private val userInfoDataSource: UserInfoDataSource): UserInfoRepository {

    override suspend fun getUserInfo(userID: Int): UserInfo? {
       return userInfoDataSource.getUserInfo(userID)
    }

    override suspend fun updateUserInfoFields(
        userID: Int,
        fieldValue: List<Pair<String, Any>>
    ): Deferred<Boolean> {
        return userInfoDataSource.updateUserInfoFields(userID, fieldValue)
    }

    override suspend fun createNewUserInfo(userID: Int): UserInfo? {
      return userInfoDataSource.createNewUserInfo(userID)
    }

    override suspend fun getUserIDByDeviceID(): Int? {
        return userInfoDataSource.getUserIDByDeviceID()
    }


}
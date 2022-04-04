package com.learning.lessons.domain.repositories

import com.learning.lessons.domain.entities.users_info.UserInfo

interface UserInfoRepository: FieldsUpdateable {
    suspend fun getUserInfo(userID: Int): UserInfo?
    suspend fun createNewUserInfo(userID: Int): UserInfo?
    suspend fun getUserIDByDeviceID(): Int?
}
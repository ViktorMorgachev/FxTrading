package com.fx_trading.lessons.domain.usecases

import com.fx_trading.lessons.domain.repositories.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserUseCase @Inject constructor(private val userRepository: UserRepository) {


    suspend fun saveResultToDatabase(quizGroupID: Int, level: Int, status: Int, userID: Int?): Boolean {
        val userID = userRepository.getUserIDByDeviceID()
        if (userID != null){
            val user = userRepository.getUserByUserID(userID)
            user?.let {
                val result = userRepository.updateUserData(user = user.copy(rank = level))
                if (result == true){
                    userRepository.saveResultTesting(it.user_id, quizGroupID, status)
                    return true
                }
            }
            return false
        } else {
            val newUser = userRepository.createNewUser()
            if (newUser != null){
                userRepository.updateUserData(user = newUser.copy(rank = level))
                userRepository.saveResultTesting(newUser.user_id, quizGroupID, status)
                userRepository.saveDeviceIDAndUserID(newUser.user_id)
                return true
                // после локально сохранить айдишник пользователя и ид устройства в преференсах

            } else return false
        }
    }

    suspend fun getUserIDByDeviceID(): Long?{
        return userRepository.getUserIDByDeviceID()
    }

}
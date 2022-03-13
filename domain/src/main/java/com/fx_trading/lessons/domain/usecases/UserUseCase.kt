package com.fx_trading.lessons.domain.usecases

import com.fx_trading.lessons.domain.repositories.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserUseCase @Inject constructor(private val userRepository: UserRepository) {
    fun saveResultToDatabase(quizGroupID: Int, level: Int, status: Int, userID: Int?) {
      CoroutineScope(Dispatchers.IO).launch {
          userRepository.checkUserByDeviceIDInDatabase()
      }

    }
}
package com.fx_trading.lessons.domain.usecases

import com.fx_trading.lessons.domain.repositories.LessonRepository
import com.fx_trading.lessons.domain.repositories.UserRepository
import javax.inject.Inject

class UserUseCase @Inject constructor(private val userRepository: UserRepository, private val lessonRepository: LessonRepository) {


    suspend fun saveFirstResultToDatabase(questionGroupID: Int, level: Int, userID: Int?): Boolean {
        val userID = userRepository.getUserIDByDeviceID()
        if (userID != null){
            val user = userRepository.getUserByUserID(userID)
            user?.let {
                val result = userRepository.updateUserData(user = user.copy(rank = level))
                if (result){
                    userRepository.firstSaveQuestionPassed(questionID = questionGroupID, userId = it.user_id)
                    return true
                }
            }
            return false
        } else {
            val newUser = userRepository.createNewUser()
            if (newUser != null){
                userRepository.updateUserData(user = newUser.copy(rank = level))
                userRepository.firstSaveQuestionPassed(questionID = questionGroupID, userId = newUser.user_id)
                userRepository.saveDeviceIDAndUserID(newUser.user_id)
                return true
            } else return false
        }
    }

    suspend fun saveLessonAndQuestionPassedToUserInfo(lessonID: Int, questionGroupID: Int, userID: Int): Boolean{
        if(userRepository.saveLessonPassed(lessonID, userID)){
          return  userRepository.saveQuestionPassed(questionID = questionGroupID, userId = userID)
        }
       return false
    }

    suspend fun getUserIDByDeviceID(): Int?{
        return userRepository.getUserIDByDeviceID()
    }

    suspend fun setLikeToLesson(lessonID: Int, userID: Int): Boolean {
        val userInfo = userRepository.getUserInfoByUserID(userID)
        userInfo?.let {
            if(it.likesLessons.contains(lessonID)) return false
            else{
               val success = lessonRepository.setLikeToLesson(lessonID)
               if (success){
                   return userRepository.updateUserInfoLessonLike(lessonID, userID)
               }
            }
        }
        return false
    }

    suspend fun setDisLikeToLesson(lessonID: Int, userID: Int): Boolean {
        val userInfo = userRepository.getUserInfoByUserID(userID)
        userInfo?.let {
            if(it.dislikesLessons.contains(lessonID)) return false
            else{
                val success = lessonRepository.setLikeToLesson(lessonID)
                if (success){
                    return userRepository.updateUserInfoLessonDisLike(lessonID, userID)
                }
            }
        }
        return false
    }

   suspend fun getCompletedLessonIds(userID: Int): List<Int> {
       val userInfo = userRepository.getUserInfoByUserID(userID)
       userInfo?.let {
           return it.passedLessons
       }
       return emptyList()
    }

}
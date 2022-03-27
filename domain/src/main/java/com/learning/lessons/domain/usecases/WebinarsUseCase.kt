package com.learning.lessons.domain.usecases

import com.learning.lessons.domain.entities.webinar.Webinar
import com.learning.lessons.domain.repositories.UserRepository
import com.learning.lessons.domain.repositories.WebinarRepository
import javax.inject.Inject

// TODO Тут описываем все случаи использования пользуясь тольоко теми функциями что имеем, переделать остальную логику в lessons, users, questions, вынести отдельно логику в UsersInfo
class WebinarsUseCase @Inject constructor(private val webinarsRepository: WebinarRepository,private val userRepository: UserRepository) {

    suspend fun getWebinars(): List<Webinar>{
       return webinarsRepository.getWebinars()
    }

    suspend fun likeWebinar(webinarID: Int, userID: Int): Boolean{
        val userInfo = userRepository.getUserInfoByUserID(userID = userID)
        if (!userInfo!!.likesWebinars.contains(webinarID)){
            val webinar = webinarsRepository.getWebinarByID(webinarID)
            if (webinar != null){
                val success = webinarsRepository.updateWebinar(webinar.copy(likes = webinar.likes + 1))
                if (success){
                    return userRepository.updateUserInfo(userInfo.copy(likesWebinars = userInfo.likesWebinars.plus(webinarID)))
                }
            } else return false
        }
        return false
    }

    suspend fun dislikeWebinar(webinarID: Int, userID: Int): Boolean{
        val userInfo = userRepository.getUserInfoByUserID(userID = userID)
        if (!userInfo!!.dislikesWebinars.contains(webinarID)){
            val webinar = webinarsRepository.getWebinarByID(webinarID)
            if (webinar != null){
                val success = webinarsRepository.updateWebinar(webinar.copy(dislikes = webinar.dislikes + 1))
                if (success){
                    return userRepository.updateUserInfo(userInfo.copy(dislikesWebinars = userInfo.dislikesWebinars.plus(webinarID)))
                }
            } else return false
        }
        return false
    }


    suspend fun getWebinarByID(webinarID: Int): Webinar? {
        return webinarsRepository.getWebinarByID(webinarID)
    }


    suspend fun getWebinarForceByID(webinarID: Int): Webinar? {
        return webinarsRepository.getWebinarForceByID(webinarID)
    }

}
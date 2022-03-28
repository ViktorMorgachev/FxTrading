package com.learning.lessons.domain.usecases

import com.learning.lessons.domain.entities.webinar.Webinar
import com.learning.lessons.domain.repositories.UserInfoRepository
import com.learning.lessons.domain.repositories.UserRepository
import com.learning.lessons.domain.repositories.WebinarRepository
import javax.inject.Inject

class WebinarsUseCase @Inject constructor(private val webinarsRepository: WebinarRepository,private val userInfoRepository: UserInfoRepository) {

    suspend fun getWebinars(): List<Webinar>{
       return webinarsRepository.getWebinars()
    }

    suspend fun likeWebinar(webinarID: Int, userID: Int): Boolean{
        val userInfo = userInfoRepository.getUserInfo(userID = userID)
        if (!userInfo!!.likesWebinars.contains(webinarID)){
            val webinar = webinarsRepository.getWebinarByID(webinarID)
            if (webinar != null){
                val success = webinarsRepository.updateWebinarField(webinarID = webinarID, webinar.copy(likes = webinar.likes + 1), "likes")
                if (success){
                    val fieldsForUpdate = listOf<Pair<String, Any>>("likes_webinars_ids" to userInfo.likesWebinars.plus(webinarID))
                    return userInfoRepository.updateUserInfoFields(userID = userID, fieldValue = fieldsForUpdate).await()
                }
            } else return false
        }
        return false
    }

    suspend fun dislikeWebinar(webinarID: Int, userID: Int): Boolean{
        val userInfo = userInfoRepository.getUserInfo(userID = userID)
        if (!userInfo!!.dislikesLessons.contains(webinarID)){
            val webinar = webinarsRepository.getWebinarByID(webinarID)
            if (webinar != null){
                val success = webinarsRepository.updateWebinarField(webinarID = webinarID, webinar.copy(dislikes = webinar.likes + 1), "dislikes")
                if (success){
                    val fieldsForUpdate = listOf<Pair<String, Any>>("dislikes_webinars_ids" to userInfo.dislikesWebinars.plus(webinarID))
                    return userInfoRepository.updateUserInfoFields(userID = userID, fieldValue = fieldsForUpdate).await()
                }
            } else return false
        }
        return false
    }


    suspend fun getWebinarByID(webinarID: Int): Webinar? {
        return webinarsRepository.getWebinarByID(webinarID)
    }

}
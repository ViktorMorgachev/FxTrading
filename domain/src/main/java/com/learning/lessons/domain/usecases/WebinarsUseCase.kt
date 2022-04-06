package com.learning.lessons.domain.usecases

import com.learning.lessons.domain.entities.users_info.ApiUserInfoFields
import com.learning.lessons.domain.entities.webinar.ApiWebinarFields
import com.learning.lessons.domain.entities.webinar.Webinar
import com.learning.lessons.domain.repositories.UserInfoRepository
import com.learning.lessons.domain.repositories.WebinarRepository
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class WebinarsUseCase @Inject constructor(private val webinarsRepository: WebinarRepository, private val userInfoRepository: UserInfoRepository) {

    suspend fun getWebinars(): List<Webinar>{
       return webinarsRepository.getWebinars()
    }

    suspend fun getWebinarsFlow(): MutableStateFlow<List<Webinar>>{
        return webinarsRepository.getWebinarsFlow()
    }

    suspend fun likeWebinar(webinarID: Int, userID: Int): Boolean{
        val userInfo = userInfoRepository.getUserInfo(userID = userID)
        if (!userInfo!!.likesWebinars.contains(webinarID)){
            val webinar = webinarsRepository.getWebinarByID(webinarID)
            if (webinar != null){
                val webinarFieldForUpdate = listOf(ApiWebinarFields.Likes.fieldName to webinar.likes + 1)
                val userInfofieldsForUpdate = listOf<Pair<String, Any>>(ApiUserInfoFields.LikesWebinars.fieldName to userInfo.likesWebinars.plus(webinarID))
                return awaitAll(webinarsRepository.updateFields(objectID = webinarID, webinarFieldForUpdate),userInfoRepository.updateFields(objectID = userID, fieldValue = userInfofieldsForUpdate) ).all { it }
            } else return false
        }
        return false
    }

    suspend fun dislikeWebinar(webinarID: Int, userID: Int): Boolean{
        val userInfo = userInfoRepository.getUserInfo(userID = userID)
        if (!userInfo!!.dislikesLessons.contains(webinarID)){
            val webinar = webinarsRepository.getWebinarByID(webinarID)
            if (webinar != null){
                val webinarFieldForUpdate = listOf(ApiWebinarFields.Dislikes.fieldName to webinar.dislikes + 1)
                val userInfofieldsForUpdate = listOf<Pair<String, Any>>(ApiUserInfoFields.DislikesWebinars.fieldName to userInfo.dislikesWebinars.plus(webinarID))
                return awaitAll(webinarsRepository.updateFields(objectID = webinarID, webinarFieldForUpdate),userInfoRepository.updateFields(objectID = userID, fieldValue = userInfofieldsForUpdate) ).all { it }
            } else return false
        }
        return false
    }


    suspend fun getWebinarByID(webinarID: Int): Webinar? {
        return webinarsRepository.getWebinarByID(webinarID)
    }

}
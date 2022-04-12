package com.learning.lessons.domain.usecases

import com.learning.lessons.domain.entities.lesson.ApiLessonsFields
import com.learning.lessons.domain.entities.user.ApiUserFields
import com.learning.lessons.domain.entities.users_info.ApiUserInfoFields
import com.learning.lessons.domain.repositories.LessonRepository
import com.learning.lessons.domain.repositories.UserInfoRepository
import com.learning.lessons.domain.repositories.UserRepository
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserInfoUseCase @Inject constructor(
    private val userInfoRepository: UserInfoRepository,
    private val lessonRepository: LessonRepository,
    private val userRepository: UserRepository
) {

    suspend fun setLikeToLesson(lessonID: Int, userID: Int): Boolean {
        userInfoRepository.getUserInfo(userID = userID)?.let { userInfo ->
            if (userInfo.likesLessons.contains(lessonID)) return false
            lessonRepository.getLessonByID(lessonID = lessonID)?.let { lesson ->
                val lessonFieldForUpdate = listOf(ApiLessonsFields.Likes.fieldName to lesson.likes + 1)
                val userInfoFieldForUpdate = listOf(ApiUserInfoFields.LikesLessons.fieldName to userInfo.likesLessons.plus(lessonID))
                return awaitAll( lessonRepository.updateFields(objectID = lessonID, lessonFieldForUpdate), userInfoRepository.updateFields(objectID = userID, userInfoFieldForUpdate)).all { it }
            }
        }
        return false
    }

    suspend fun setDisLikeToLesson(lessonID: Int, userID: Int): Boolean {
        userInfoRepository.getUserInfo(userID = userID)?.let { userInfo ->
            if (userInfo.dislikesLessons.contains(lessonID)) return false
            lessonRepository.getLessonByID(lessonID = lessonID)?.let { lesson ->
                val lessonFieldForUpdate = listOf(ApiLessonsFields.Dislikes.fieldName to lesson.dislikes + 1)
                val userInfoFieldForUpdate = listOf(ApiUserInfoFields.DislikesLessons.fieldName to userInfo.dislikesLessons.plus(lessonID))
                return awaitAll(lessonRepository.updateFields(objectID = lessonID, lessonFieldForUpdate), userInfoRepository.updateFields(objectID = userID, userInfoFieldForUpdate)).all { it }
            }
        }
        return false
    }

    suspend fun saveFirstTesting(questionGroupID: Int, rank: Int, userID: Int?): Boolean {
        if (userID == null) {
            val userid = userInfoRepository.getUserIDByDeviceID()
            if (userid != null) {
                return saveFirstTesting(
                    questionGroupID = questionGroupID,
                    rank = rank,
                    userID = userid
                )
            } else {
                val lastUserID = userRepository.getLastUserID() + 1
                val userInfo = userInfoRepository.createNewUserInfo(lastUserID)
                val user = userRepository.createNewUser(lastUserID)
                if (userInfo != null && user != null) {
                    return saveFirstTesting(
                        questionGroupID = questionGroupID,
                        rank = rank,
                        userID = user.user_id
                    )
                }
            }
        } else {
            return saveFirstTesting(
                questionGroupID = questionGroupID,
                rank = rank,
                userID = userID
            )
        }
        return false
    }

    suspend fun saveTestingLesson(questionGroupID: Int, userID: Int, lessonID: Int) =
        withContext(Dispatchers.IO) {
            run {
                val userInfo = userInfoRepository.getUserInfo(userID)
                val user = userRepository.getUserByUserID(userID)
                if (userInfo != null && user != null) {
                    val fieldsForUpdate = listOf<Pair<String, Any>>(
                        ApiUserInfoFields.PassedQuestionsIDs.fieldName to userInfo.questionsIDs.plus(questionGroupID),
                        ApiUserInfoFields.PassedLessons.fieldName to userInfo.passedLessons.plus(lessonID)
                    )
                    return@withContext userInfoRepository.updateFields(
                        objectID = userID,
                        fieldValue = fieldsForUpdate
                    ).await()
                } else {
                    return@withContext false
                }
            }
        }

    suspend fun saveTestingArticle(questionGroupID: Int, userID: Int, articleID: Int) =
        withContext(Dispatchers.IO) {
            run {
                val userInfo = userInfoRepository.getUserInfo(userID)
                val user = userRepository.getUserByUserID(userID)
                if (userInfo != null && user != null) {
                    val fieldsForUpdate = listOf<Pair<String, Any>>(
                        ApiUserInfoFields.PassedQuestionsIDs.fieldName to userInfo.questionsIDs.plus(questionGroupID),
                        ApiUserInfoFields.PassedArticles.fieldName to userInfo.passedArticles.plus(articleID)
                    )
                    return@withContext userInfoRepository.updateFields(
                        objectID = userID,
                        fieldValue = fieldsForUpdate
                    ).await()
                } else {
                    return@withContext false
                }
            }
        }

    private suspend fun saveFirstTesting(questionGroupID: Int, rank: Int, userID: Int): Boolean{
        val userInfo = userInfoRepository.getUserInfo(userID)
        val user = userRepository.getUserByUserID(userID)
        if (userInfo != null && user != null) {
            if(user.user_id != userInfo.user_id){
                throw RuntimeException("UserID ${user.user_id} != UserInfoID ${userInfo.user_id}")
            }
            val fieldsUserInfoForUpdate = listOf<Pair<String, Any>>(ApiUserInfoFields.PassedQuestionsIDs.fieldName to userInfo.questionsIDs.plus(questionGroupID))
            val fieldsUserForUpdate = listOf(ApiUserFields.Rank.fieldName to rank)
            return awaitAll(userInfoRepository.updateFields(objectID = userID, fieldsUserInfoForUpdate), userRepository.updateFields(objectID = userID, fieldsUserForUpdate) ).all { it }
        } else {
            return false
        }
    }

    suspend fun getCompletedLessonIds(userID: Int): List<Int> {
        userInfoRepository.getUserInfo(userID = userID)?.let { userInfo ->
            return userInfo.passedLessons
        }
        return emptyList()
    }

    suspend fun getCompletedCoursesIds(userID: Int): List<Int> {
        userInfoRepository.getUserInfo(userID = userID)?.let { userInfo ->
            return userInfo.passedCourses
        }
        return emptyList()
    }


    suspend fun getCompletedArticlesIds(userID: Int): List<Int> {
        userInfoRepository.getUserInfo(userID = userID)?.let { userInfo ->
            return userInfo.passedArticles
        }
        return emptyList()
    }


    suspend fun getUserIDByDeviceID(): Int? {
        return userInfoRepository.getUserIDByDeviceID()
    }


}
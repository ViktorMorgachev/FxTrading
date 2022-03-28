package com.learning.lessons.domain.usecases

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
                lessonRepository.updateLessonField(lessonID = lesson.id, lesson.likes + 1, "likes")
                    .await()
                return userInfoRepository.updateUserInfoFields(
                    userID,
                    fieldValue = listOf("likes_lessons_ids" to userInfo.likesLessons.plus(lessonID))
                ).await()
            }
        }
        return false
    }

    suspend fun setDisLikeToLesson(lessonID: Int, userID: Int): Boolean {
        userInfoRepository.getUserInfo(userID = userID)?.let { userInfo ->
            if (userInfo.dislikesLessons.contains(lessonID)) return false
            lessonRepository.getLessonByID(lessonID = lessonID)?.let { lesson ->
                lessonRepository.updateLessonField(
                    lessonID = lesson.id,
                    lesson.likes + 1,
                    "dislikes"
                ).await()
                return userInfoRepository.updateUserInfoFields(
                    userID,
                    fieldValue = listOf(
                        "dislikes_lessons_ids" to userInfo.dislikesLessons.plus(lessonID)
                    )
                ).await()
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

    suspend fun saveTesting(questionGroupID: Int, userID: Int, lessonID: Int) =
        withContext(Dispatchers.IO) {
            run {
                val userInfo = userInfoRepository.getUserInfo(userID)
                val user = userRepository.getUserByUserID(userID)
                if (userInfo != null && user != null) {
                    val fieldsForUpdate = listOf<Pair<String, Any>>(
                        "passed_questions_ids" to userInfo.questionsIDs.plus(questionGroupID),
                        "passed_lessons_ids" to userInfo.passedLessons.plus(lessonID)
                    )
                    try {
                        return@withContext userInfoRepository.updateUserInfoFields(
                            userID = userID,
                            fieldValue = fieldsForUpdate
                        ).await()
                    } catch (e: Throwable) {
                        return@withContext false
                    }
                } else {
                    return@withContext false
                }
            }
        }

    private suspend fun saveFirstTesting(questionGroupID: Int, rank: Int, userID: Int): Boolean{
        val userInfo = userInfoRepository.getUserInfo(userID)
        val user = userRepository.getUserByUserID(userID)
        if (userInfo != null && user != null) {
            val fieldsForUpdate = listOf<Pair<String, Any>>(
                "passed_questions_ids" to userInfo.questionsIDs.plus(questionGroupID)
            )
            try {
                return awaitAll(
                    userInfoRepository.updateUserInfoFields(
                        userID = userID,
                        fieldValue = fieldsForUpdate
                    ),
                    userRepository.updateUserField(
                        userID,
                        fieldValue = listOf("rank" to rank)
                    )
                ).all { it }
            } catch (e: Throwable) {
                return false
            }
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

    suspend fun getUserIDByDeviceID(): Int? {
        return userInfoRepository.getUserIDByDeviceID()
    }


}
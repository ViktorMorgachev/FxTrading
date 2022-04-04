package com.learning.lessons.domain.usecases

import com.learning.lessons.domain.entities.course.ApiCourseFields
import com.learning.lessons.domain.entities.course.Course
import com.learning.lessons.domain.entities.lesson.ApiLessonsFields
import com.learning.lessons.domain.entities.lesson.Lesson
import com.learning.lessons.domain.entities.users_info.ApiUserInfoFields
import com.learning.lessons.domain.entities.webinar.Webinar
import com.learning.lessons.domain.repositories.CourseRepository
import com.learning.lessons.domain.repositories.UserInfoRepository
import com.learning.lessons.domain.repositories.UserRepository
import com.learning.lessons.domain.repositories.WebinarRepository
import kotlinx.coroutines.awaitAll
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CourseUseCase @Inject constructor(private val courseRepository: CourseRepository, private val userInfoRepository: UserInfoRepository) {

    suspend fun getCourses(): List<Course>{
       return courseRepository.getCourses()
    }

    suspend fun likeCourse(courseID: Int, userID: Int): Boolean{
        val userInfo = userInfoRepository.getUserInfo(userID = userID)
        if (!userInfo!!.likesCourses.contains(courseID)){
            val course = courseRepository.getCourseByID(courseID)
            if (course != null){
                val courseFieldForUpdate = listOf(ApiCourseFields.Likes.fieldName to course.likes + 1)
                val fieldsForUpdate = listOf<Pair<String, Any>>(ApiUserInfoFields.LikesCourses.fieldName to userInfo.likesCourses.plus(courseID))
                return awaitAll(courseRepository.updateFields(objectID = courseID, courseFieldForUpdate),userInfoRepository.updateFields(objectID = userID, fieldValue = fieldsForUpdate) ).all { it }
            } else return false
        }
        return false
    }

    suspend fun dislikeCourse(courseID: Int, userID: Int): Boolean{
        val userInfo = userInfoRepository.getUserInfo(userID = userID)
        if (!userInfo!!.dislikesCourses.contains(courseID)){
            val course = courseRepository.getCourseByID(courseID)
            if (course != null){
                val courseFieldForUpdate = listOf(ApiCourseFields.Dislikes.fieldName to course.dislikes + 1)
                val fieldsForUpdate = listOf<Pair<String, Any>>(ApiUserInfoFields.DislikesCourses.fieldName to userInfo.dislikesCourses.plus(courseID))
                return awaitAll(courseRepository.updateFields(objectID = courseID, courseFieldForUpdate), userInfoRepository.updateFields(objectID = userID, fieldValue = fieldsForUpdate)).all { it }
            } else return false
        }
        return false
    }


    suspend fun getCourseByID(courseID: Int): Course? {
        return courseRepository.getCourseByID(courseID)
    }

}
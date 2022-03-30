package com.learning.lessons.domain.usecases

import com.learning.lessons.domain.entities.course.Course
import com.learning.lessons.domain.entities.webinar.Webinar
import com.learning.lessons.domain.repositories.CourseRepository
import com.learning.lessons.domain.repositories.UserInfoRepository
import com.learning.lessons.domain.repositories.UserRepository
import com.learning.lessons.domain.repositories.WebinarRepository
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
                val success = courseRepository.updateCourseField(courseID = courseID, course.copy(likes = course.likes + 1), "likes").await()
                if (success){
                    val fieldsForUpdate = listOf<Pair<String, Any>>("likes_courses_ids" to userInfo.likesCourses.plus(courseID))
                    return userInfoRepository.updateUserInfoFields(userID = userID, fieldValue = fieldsForUpdate).await()
                }
            } else return false
        }
        return false
    }

    suspend fun dislikeCourse(courseID: Int, userID: Int): Boolean{
        val userInfo = userInfoRepository.getUserInfo(userID = userID)
        if (!userInfo!!.dislikesCourses.contains(courseID)){
            val course = courseRepository.getCourseByID(courseID)
            if (course != null){
                val success = courseRepository.updateCourseField(courseID = courseID, course.copy(dislikes = course.dislikes + 1), "dislikes").await()
                if (success){
                    val fieldsForUpdate = listOf<Pair<String, Any>>("dislikes_courses_ids" to userInfo.dislikesWebinars.plus(courseID))
                    return userInfoRepository.updateUserInfoFields(userID = userID, fieldValue = fieldsForUpdate).await()
                }
            } else return false
        }
        return false
    }


    suspend fun getCourseByID(courseID: Int): Course? {
        return courseRepository.getCourseByID(courseID)
    }

}
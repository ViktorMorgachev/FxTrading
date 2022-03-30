package com.learning.lessons.data.repositories.courses

import com.learning.lessons.data.api.course.ApiCourse
import kotlinx.coroutines.flow.Flow

interface CourseRemoteRepository {
    suspend fun getCourses(): List<ApiCourse>
    suspend fun getCourseByID(id: Int): ApiCourse?
    suspend fun updateCourseField(courseID: Int, fieldValue: Any, field: String): Flow<Boolean>
}
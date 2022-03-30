package com.learning.lessons.domain.repositories

import com.learning.lessons.domain.entities.course.Course
import kotlinx.coroutines.Deferred

interface CourseRepository {
    suspend fun getCourses(): List<Course>
    suspend fun getCourseByID(courseID: Int): Course?
    suspend fun updateCourseField(courseID: Int, fieldValue: Any, field: String): Deferred<Boolean>
}
package com.learning.lessons.domain.repositories

import com.learning.lessons.domain.entities.course.Course
import kotlinx.coroutines.Deferred

interface CourseRepository: FieldsUpdateable {
    suspend fun getCourses(): List<Course>
    suspend fun getCourseByID(courseID: Int): Course?
}
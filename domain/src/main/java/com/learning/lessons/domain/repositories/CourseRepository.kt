package com.learning.lessons.domain.repositories

import com.learning.lessons.domain.entities.course.Course
import com.learning.lessons.domain.entities.webinar.Webinar
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.MutableStateFlow

interface CourseRepository: FieldsUpdateable {
    suspend fun getCourses(): List<Course>
    suspend fun getCourseByID(courseID: Int): Course?
    suspend fun getCoursesFlow(): MutableStateFlow<List<Course>> = MutableStateFlow(listOf())
}
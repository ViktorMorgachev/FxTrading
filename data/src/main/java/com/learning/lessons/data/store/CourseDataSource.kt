package com.learning.lessons.data.store

import com.learning.lessons.data.mappers.toCourse
import com.learning.lessons.data.mappers.toWebinar
import com.learning.lessons.data.repositories.courses.CourseRemoteRepository
import com.learning.lessons.domain.entities.course.Course
import com.learning.lessons.domain.entities.webinar.Webinar
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CourseDataSource @Inject constructor(private val courseRemoteRepository: CourseRemoteRepository){
    suspend fun getCourses(): List<Course> {
        return courseRemoteRepository.getCourses().map { it.toCourse() }
    }

    suspend fun getCourseByID(id: Int): Course? {
        return courseRemoteRepository.getCourseByID(id)?.toCourse()
    }

    suspend fun updateCourseField(
        courseID: Int,
        fieldValue: Any,
        field: String
    ): Deferred<Boolean> = withContext(Dispatchers.IO) {
        return@withContext async {courseRemoteRepository.updateCourseField(courseID, fieldValue, field).last() }
    }

}
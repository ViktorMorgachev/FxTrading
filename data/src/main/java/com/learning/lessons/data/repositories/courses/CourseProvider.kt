package com.learning.lessons.data.repositories.courses

import com.learning.lessons.data.store.CourseDataSource
import com.learning.lessons.domain.entities.course.Course
import com.learning.lessons.domain.repositories.CourseRepository
import kotlinx.coroutines.Deferred
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CourseProvider @Inject constructor(private val courseDataSource: CourseDataSource) : CourseRepository {
    override suspend fun getCourses(): List<Course> {
        return courseDataSource.getCourses()
    }

    override suspend fun getCourseByID(courseID: Int): Course? {
        return courseDataSource.getCourseByID(courseID)
    }

    override suspend fun updateFields(
        objectID: Int,
        fieldValue: List<Pair<String, Any>>
    ): Deferred<Boolean> {
        return courseDataSource.updateCourseFields(objectID, fieldValue)
    }


}
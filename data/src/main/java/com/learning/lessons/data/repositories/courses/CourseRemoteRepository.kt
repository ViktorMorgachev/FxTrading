package com.learning.lessons.data.repositories.courses

import com.learning.lessons.data.api.course.ApiCourse
import com.learning.lessons.data.repositories.AutoUpdatable
import com.learning.lessons.data.repositories.FieldsUpdateable
import kotlinx.coroutines.flow.Flow

interface CourseRemoteRepository: FieldsUpdateable, AutoUpdatable {
    suspend fun getCourses(): List<ApiCourse>
    suspend fun getCourseByID(id: Int): ApiCourse?
}
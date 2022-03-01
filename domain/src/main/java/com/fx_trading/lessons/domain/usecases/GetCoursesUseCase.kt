package com.fx_trading.lessons.domain.usecases

import com.fx_trading.lessons.domain.entities.course.Course

interface GetCoursesUseCase {
   suspend fun getCourses(): List<Course>
}
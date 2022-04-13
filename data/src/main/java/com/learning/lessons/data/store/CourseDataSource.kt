package com.learning.lessons.data.store

import com.learning.lessons.data.mappers.toCourse
import com.learning.lessons.data.mappers.toWebinar
import com.learning.lessons.data.repositories.courses.CourseRemoteRepository
import com.learning.lessons.domain.entities.course.Course
import com.learning.lessons.domain.entities.webinar.Webinar
import com.learning.lessons.utils.utils.Logger
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.last
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CourseDataSource @Inject constructor(private val courseRemoteRepository: CourseRemoteRepository){

    private val courses: MutableStateFlow<List<Course>> = MutableStateFlow(listOf())
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    init {
        subscribe()
    }

    private fun subscribe() {
        val update = {
            Logger.log("CourseDataSource", "update.invoke()")
            coroutineScope.launch {
                courses.emit(courseRemoteRepository.getCourses().map { it.toCourse() })
            }
        }
        coroutineScope.launch {
            courses.emit(courseRemoteRepository.getCourses().map { it.toCourse() })
            courseRemoteRepository.subscribeToChangesCollection() {
                update.invoke()
            }
            courses.value.forEach {
                courseRemoteRepository.subscribeToChangeDocument(it.id) {
                    update.invoke()
                }
            }
        }
    }

    suspend fun getCoursesFlow(): MutableStateFlow<List<Course>> {
        return courses
    }

    suspend fun getCourses(): List<Course> {
        return if(courses.value.isNotEmpty()) courses.value else
            courseRemoteRepository.getCourses().map { it.toCourse() }
    }

    suspend fun getCourseByID(id: Int): Course? {
        return courses.value.firstOrNull { it.id == id } ?: courseRemoteRepository.getCourseByID(id)?.toCourse()
    }

    suspend fun updateCourseFields(
        courseID: Int,
        fieldValues: List<Pair<String, Any>>
    ): Deferred<Boolean> = withContext(Dispatchers.IO){
        return@withContext async {courseRemoteRepository.updateFields(courseID, fieldValues).last() }
    }

}
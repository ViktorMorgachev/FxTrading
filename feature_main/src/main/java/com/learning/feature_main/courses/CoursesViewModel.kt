package com.learning.feature_main.courses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learning.common.State
import com.learning.common.createState
import com.learning.lessons.domain.entities.course.Course
import com.learning.lessons.domain.entities.webinar.Webinar
import com.learning.lessons.domain.usecases.CourseUseCase
import com.learning.lessons.domain.usecases.UserInfoUseCase
import com.learning.lessons.utils.utils.Logger
import data.DataStoreHelper
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoursesViewModel @Inject constructor(
    private val userInfoUseCase: UserInfoUseCase,
    private val courseUseCase: CourseUseCase,
    private var dataStoreHelper: DataStoreHelper,
) : ViewModel() {

    var passedCourses: List<Int> = listOf()
        private set

    fun getData() = flow<State<Pair<List<Course>, List<Int>>>> {
        emit(State.LoadingState)
        try {
            dataStoreHelper.userID().collect {
                val courses = courseUseCase.getCourses()
                passedCourses = userInfoUseCase.getCompletedCoursesIds(it)
                emit(State.DataState(courses to passedCourses))
            }
        } catch (e: Exception) {
            Logger.log("CoursesViewModel", exception = e)
            emit(State.ErrorState(e))
        }
    }

    suspend fun subscribeToCourses(): MutableStateFlow<List<Course>> {
        return courseUseCase.getCoursesFlow()
    }

    fun likeCourse(courseID: Int) = flow<State<Course>> {
        try {
            dataStoreHelper.userID().collect {
                val success = courseUseCase.likeCourse(courseID = courseID, userID = it)
                if (success){
                    emit(createState(courseUseCase.getCourseByID(courseID)))
                }
            }
        } catch (e: Exception){
            Logger.log("WebinarsViewModel", exception = e)
            emit(State.ErrorState(exception = e))
        }
    }

}
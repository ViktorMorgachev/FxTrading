package com.learning.lessons.feature_main.ui.courses

import androidx.lifecycle.ViewModel
import com.learning.common.State
import com.learning.common.createState
import com.learning.lessons.domain.entities.course.Course
import com.learning.lessons.domain.entities.webinar.Webinar
import com.learning.lessons.domain.usecases.CourseUseCase
import com.learning.lessons.domain.usecases.UserInfoUseCase
import com.learning.lessons.utils.utils.Logger
import data.DataStoreHelper
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CoursesViewModel @Inject constructor(
    private val userInfoUseCase: UserInfoUseCase,
    private val courseUseCase: CourseUseCase,
    private var dataStoreHelper: DataStoreHelper,
) : ViewModel() {

    fun getData() = flow<State<Pair<List<Course>, List<Int>>>> {
        emit(State.LoadingState)
        try {
            dataStoreHelper.userID().collect {
                val lessons = courseUseCase.getCourses()
                val completedLessons = userInfoUseCase.getCompletedCoursesIds(it)
                emit(State.DataState(Pair(first = lessons, second = completedLessons)))
            }
        } catch (e: Exception) {
            Logger.log("CoursesViewModel", exception = e)
            emit(State.ErrorState(e))
        }
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
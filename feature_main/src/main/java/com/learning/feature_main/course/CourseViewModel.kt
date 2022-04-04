package com.learning.feature_main.course

import androidx.lifecycle.ViewModel
import com.learning.common.State
import com.learning.common.createState
import com.learning.lessons.domain.entities.course.Course
import com.learning.lessons.domain.entities.lesson.Lesson
import com.learning.lessons.domain.usecases.CourseUseCase
import com.learning.lessons.domain.usecases.LessonsUseCase
import com.learning.lessons.domain.usecases.UserInfoUseCase
import com.learning.lessons.utils.utils.Logger
import data.DataStoreHelper
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CourseViewModel @Inject constructor(
    private val courseUseCase: CourseUseCase,
    private val userInfoUseCase: UserInfoUseCase,
    private val dataStoreHelper: DataStoreHelper,
    private val lessonsUseCase: LessonsUseCase
) : ViewModel() {

    fun getCourse(courseID: Int) = flow<State<Pair<Course, List<Int>>>> {
        emit(State.LoadingState)
        try {
            dataStoreHelper.userID().collect {
                val course = courseUseCase.getCourseByID(courseID)
                val passedLessons = userInfoUseCase.getCompletedLessonIds(userID = it)
                if (course != null)
                    emit(State.DataState(course to passedLessons))
                else
                    emit(State.EmptyState)
            }
        } catch (e: Exception) {
            Logger.log("CourseViewModel", exception = e)
            emit(State.ErrorState(e))
        }
    }

    fun getCourseLessons(lessonsIDs: List<Int>) = flow<State<List<Lesson>>>{
        emit(State.LoadingState)
        try {
           val lessons = lessonsUseCase.getLessonsByIDs(lessonsIDs)
            emit(createState(lessons))
        } catch (e: Exception) {
            Logger.log("CourseViewModel", exception = e)
            emit(State.ErrorState(e))
        }
    }

    fun likeLesson(lessonID: Int)= flow<State<Lesson>>{
        emit(State.LoadingState)
        try {
            dataStoreHelper.userID().collect {
                val success = userInfoUseCase.setLikeToLesson(lessonID, it)
                if (success){
                    val lesson = lessonsUseCase.getLessonByID(lessonID)
                    emit(createState(lesson))
                }
            }
        } catch (e: Exception){
            Logger.log("CourseViewModel", exception = e)
            emit(State.ErrorState(e))
        }
    }

}
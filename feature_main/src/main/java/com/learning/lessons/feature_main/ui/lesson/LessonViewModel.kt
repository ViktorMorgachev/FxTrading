package com.learning.lessons.feature_main.ui.lesson

import androidx.lifecycle.ViewModel
import com.learning.common.State
import com.learning.common.createState
import com.learning.lessons.domain.entities.lesson.Lesson
import com.learning.lessons.domain.usecases.LessonsUseCase
import com.learning.lessons.domain.usecases.UserInfoUseCase
import com.learning.lessons.utils.utils.Logger
import data.DataStoreHelper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LessonViewModel @Inject constructor(val lessonsUseCase: LessonsUseCase, val userInfoUseCase: UserInfoUseCase, val  dataStoreHelper: DataStoreHelper) : ViewModel() {

    fun getLesson(lessonID: Int) = flow {
        emit(State.LoadingState)
        try {
            delay(500)
            val lesson = lessonsUseCase.getLessonByID(lessonID)
            emit(createState(lesson))
        } catch (e: Exception) {
            Logger.log("LessonViewModel", exception = e)
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
            Logger.log("LessonViewModel", exception = e)
            emit(State.ErrorState(e))
        }
    }

    fun dislikeLesson(lessonID: Int)= flow<State<Lesson>>{
        emit(State.LoadingState)
        try {
            dataStoreHelper.userID().collect {
                val success = userInfoUseCase.setDisLikeToLesson(lessonID, it)
                if (success){
                    val lesson = lessonsUseCase.getLessonByID(lessonID)
                    emit(createState(lesson))
                }
            }
        } catch (e: Exception){
            Logger.log("LessonViewModel", exception = e)
            emit(State.ErrorState(e))
        }
    }

    fun getLessonsByTags(tags: List<String>) = flow<State<Pair<List<Lesson>, List<Int>>>>{
        emit(State.LoadingState)
        try {
            dataStoreHelper.userID().collect {
                val lessons = lessonsUseCase.getLessonsByTags(tags)
                val completedLessons = userInfoUseCase.getCompletedLessonIds(it)
                emit(State.DataState(Pair(first = lessons, second = completedLessons)))
            }


        } catch (e: Exception){
            Logger.log("LessonViewModel", exception = e)
            emit(State.ErrorState(e))
        }
    }
}
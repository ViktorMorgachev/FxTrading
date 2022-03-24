package com.fx_trading.lessons.feature_main.ui.lesson

import androidx.lifecycle.ViewModel
import com.fx_trading.common.State
import com.fx_trading.common.createState
import com.fx_trading.lessons.domain.entities.lesson.Lesson
import com.fx_trading.lessons.domain.usecases.LessonsUseCase
import com.fx_trading.lessons.domain.usecases.UserUseCase
import com.fx_trading.lessons.utils.utils.Logger
import data.DataStoreHelper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LessonViewModel @Inject constructor(val lessonsUseCase: LessonsUseCase,val userUseCase: UserUseCase,val  dataStoreHelper: DataStoreHelper) : ViewModel() {

    fun getLesson(lessonID: Long) = flow {
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

    fun likeLesson(lessonID: Long)= flow{
        emit(State.LoadingState)
        try {
            dataStoreHelper.userID().collect {
                val success = userUseCase.setLikeToLesson(lessonID, it)
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

    fun dislikeLesson(lessonID: Long)= flow{
        emit(State.LoadingState)
        try {
            dataStoreHelper.userID().collect {
                val success = userUseCase.setDisLikeToLesson(lessonID, it)
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

    fun getLessonsByTags(tags: List<String>) = flow{
        emit(State.LoadingState)
        try {
            dataStoreHelper.userID().collect {
                val lessons = lessonsUseCase.getLessonsByTags(tags)
                val completedLessons = userUseCase.getCompletedLessonIds(it.toInt())
                emit(State.DataState(Pair(first = lessons, second = completedLessons)))
            }


        } catch (e: Exception){
            Logger.log("LessonViewModel", exception = e)
            emit(State.ErrorState(e))
        }
    }
}
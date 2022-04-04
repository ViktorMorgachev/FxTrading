package com.learning.feature_main.lessons

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.airbnb.paris.R2.id.async
import com.learning.common.State
import com.learning.lessons.domain.entities.lesson.Lesson
import com.learning.lessons.domain.usecases.LessonsUseCase
import com.learning.lessons.domain.usecases.UserInfoUseCase
import com.learning.lessons.utils.utils.Logger
import data.DataStoreHelper
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LessonsViewModel @Inject constructor(
    private val userInfoUseCase: UserInfoUseCase,
    private val lessonsUseCase: LessonsUseCase,
    private var dataStoreHelper: DataStoreHelper,
) : ViewModel() {

    val likedLesson: MutableLiveData<Lesson?> = MutableLiveData(null)

    fun getData() = flow<State<Pair<List<Lesson>, List<Int>>>> {
        emit(State.LoadingState)
        try {
            dataStoreHelper.userID().collect {
                val lessons = lessonsUseCase.getLessons()
                val completedLessons = userInfoUseCase.getCompletedLessonIds(it)
                emit(State.DataState(lessons to completedLessons))

            }
        } catch (e: Exception) {
            Logger.log("LessonsViewModel", exception = e)
            emit(State.ErrorState(e))
        }
    }

    fun likeLesson(lessonID: Int) {
        viewModelScope.launch {
            try {
                dataStoreHelper.userID().collect { userID ->
                    val success = userInfoUseCase.setLikeToLesson(lessonID, userID)
                    if (success) {
                        val lesson = lessonsUseCase.getLessonByID(lessonID)
                        lesson?.let {
                            likedLesson.postValue(it)
                        }
                    }
                }
            } catch (e: Exception) {
                Logger.log("LessonsViewModel", exception = e)
            }
        }

    }
}
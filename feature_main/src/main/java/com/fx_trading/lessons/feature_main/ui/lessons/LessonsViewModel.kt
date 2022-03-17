package com.fx_trading.lessons.feature_main.ui.lessons

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fx_trading.common.State
import com.fx_trading.lessons.domain.entities.lesson.Lesson
import com.fx_trading.lessons.domain.usecases.LessonsUseCase
import com.fx_trading.lessons.utils.utils.Logger
import com.fx_trading.navigation.Router
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LessonsViewModel @Inject constructor(
    private val router: Router,
    private val lessonsUseCase: LessonsUseCase
) : ViewModel() {

    val likedLesson: MutableLiveData<Lesson?> = MutableLiveData(null)

    fun getData() = flow {
        emit(State.LoadingState)
        try {
            delay(500)
            val data = lessonsUseCase.getLessons()
            emit(State.DataState(data))
        } catch (e: Exception) {
            Logger.log("ExampleViewModel", exception = e)
            emit(State.ErrorState(e))
        }
    }

    fun likeLesson(lessonID: Int) {
        viewModelScope.launch {
            try {
                delay(500)
                val lesson = lessonsUseCase.getLessons().firstOrNull { it.id == lessonID }
                likedLesson.postValue(lesson?.copy(likes = lesson.likes + 1))
            } catch (e: Exception) {
                Logger.log("ExampleViewModel", exception = e)
            }
        }

    }
}
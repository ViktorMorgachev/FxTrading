package com.fx_trading.lessons.feature_main.ui.lessons

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fx_trading.common.State
import com.fx_trading.lessons.domain.entities.lesson.Lesson
import com.fx_trading.lessons.domain.usecases.LessonsUseCase
import com.fx_trading.lessons.domain.usecases.UserUseCase
import com.fx_trading.lessons.utils.utils.Logger
import com.fx_trading.navigation.Router
import data.DataStoreHelper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LessonsViewModel @Inject constructor(
    private val router: Router,
    private val lessonsUseCase: LessonsUseCase,
    private val usersUseCase: UserUseCase,
    private var dataStoreHelper: DataStoreHelper
) : ViewModel() {

    val likedLesson: MutableLiveData<Lesson?> = MutableLiveData(null)

    fun getData() = flow {
        emit(State.LoadingState)
        try {
            delay(500)
            dataStoreHelper.userID().collect {
                val lessons = lessonsUseCase.getLessons()
                val completedLessons = usersUseCase.getCompletedLessonIds(it.toInt())
                emit(State.DataState(Pair(first = lessons, second = completedLessons)))
            }
        } catch (e: Exception) {
            Logger.log("ExampleViewModel", exception = e)
            emit(State.ErrorState(e))
        }
    }

    fun likeLesson(lessonID: Int) {
        viewModelScope.launch {
            try {
                delay(500)
                // Проверить в UsersInfo лайкал ли он урок
                // Поставить лайк уроку, проверить что всё ок
                // Либо ничего не делать
                dataStoreHelper.userID().collect { userID ->
                    val success = usersUseCase.setLikeToLesson(lessonID, userID)
                    if (success) {
                        val lesson = lessonsUseCase.getLessonByID(lessonID)
                        lesson?.let {
                            likedLesson.postValue(it)
                        }
                    }
                }
                //val lesson = lessonsUseCase.getLessons().firstOrNull { it.id == lessonID }
                //likedLesson.postValue(lesson?.copy(likes = lesson.likes + 1))
            } catch (e: Exception) {
                Logger.log("ExampleViewModel", exception = e)
            }
        }

    }
}
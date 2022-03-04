package com.fx_trading.lessons.feature_main.ui.lesson


import com.fx_trading.lessons.domain.usecases.LessonsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class LessonPresenter @Inject constructor(val lessonUseCase: LessonsUseCase) :
    MvpPresenter<LessonView>() {

    fun getLesson(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val lesson = lessonUseCase.getLessonById(id)
            viewState.showLesson(lesson)
        }
    }
}
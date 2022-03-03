package com.fx_trading.lessons.ui.quiz


import com.fx_trading.lessons.domain.usecases.QuestionUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class QuiestionsPresenter @Inject constructor(val questionUseCase: QuestionUseCase) :
    MvpPresenter<QuiestionsView>() {

    fun getLesson(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val lesson = questionUseCase.getQuizzes()
            viewState.showQuestion(lesson.first().questions.first())
        }
    }
}
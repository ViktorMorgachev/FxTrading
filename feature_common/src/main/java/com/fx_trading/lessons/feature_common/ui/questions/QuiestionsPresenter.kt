package com.fx_trading.lessons.feature_common.ui.questions


import com.fx_trading.lessons.domain.usecases.QuestionUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class QuiestionsPresenter @Inject constructor() : MvpPresenter<QuiestionsView>() {

    @Inject
    lateinit var questionUseCase: QuestionUseCase

    fun fetchFirstQuestions() {
        CoroutineScope(Dispatchers.IO).launch {
            val lesson = questionUseCase.getQuestionsGroup()
            viewState.showQuestion(lesson.first().questions.first())
        }
    }

    fun nextQuestion(){

    }
}
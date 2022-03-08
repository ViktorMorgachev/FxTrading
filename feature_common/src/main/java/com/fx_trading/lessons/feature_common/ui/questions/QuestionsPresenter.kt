package com.fx_trading.lessons.feature_common.ui.questions


import com.fx_trading.lessons.domain.usecases.QuestionUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class QuestionsPresenter @Inject constructor(var questionUseCase: QuestionUseCase) : MvpPresenter<QuiestionsView>() {



    fun fetchFirstQuestions() {
        CoroutineScope(Dispatchers.IO).launch {
           val questionGroup = questionUseCase.getQuestionStartExamQuestionGroup()
            viewState.showQuestion(questionGroup.questions.first())
        }
    }

    fun nextQuestion(){

    }
}
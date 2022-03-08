package com.fx_trading.lessons.feature_common.ui.questions


import com.fx_trading.lessons.domain.entities.quiz.QuestionsGroup
import com.fx_trading.lessons.domain.usecases.QuestionUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class QuestionsPresenter @Inject constructor(var questionUseCase: QuestionUseCase) : MvpPresenter<QuiestionsView>() {


    private var successCount = 0
    private var data: QuestionsGroup? = null
    private var questionsSize = 0
    private var step = 1

    fun fetchFirstQuestions() {
        CoroutineScope(Dispatchers.IO).launch {
            data = questionUseCase.getQuestionStartExamQuestionGroup()
            data?.let {
                questionsSize = it.questions.size
                nextQuestion()
            }
        }
    }

    fun increaseSuccess(){
        successCount++
    }

    fun nextQuestion(){
        data?.let {
            viewState.showQuestion(it.questions.first(), questionsSize, step, successCount)
            it.questions.toMutableList().remove(it.questions.first())
            step++
        }
    }
}
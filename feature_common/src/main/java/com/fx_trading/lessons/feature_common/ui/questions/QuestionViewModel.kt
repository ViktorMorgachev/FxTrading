package com.fx_trading.lessons.feature_common.ui.questions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fx_trading.lessons.domain.entities.quiz.Answer
import com.fx_trading.lessons.domain.entities.quiz.Question
import com.fx_trading.lessons.domain.entities.quiz.QuestionsGroup
import com.fx_trading.lessons.domain.usecases.QuestionUseCase
import com.fx_trading.navigation.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class QuestionAction {
    data class ShowQuestionAction(
        val quiestion: Question,
        val questionSize: Int,
        val step: Int,
        val succesCount: Int
    ) : QuestionAction()

    object ShowLastScreenAction : QuestionAction()
    object ShowLoadingAction : QuestionAction()
    object ShowResultAction : QuestionAction()
}

class QuestionViewModel @Inject constructor(
    private val router: Router,
    var questionUseCase: QuestionUseCase
) : ViewModel() {

    private var successCount = 0
    private var questionsSize = 0
    private var step = 1

    private val questionGroup: QuestionsGroup? = null

    val uiData = MutableLiveData<QuestionAction>(QuestionAction.ShowLoadingAction)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val result = questionUseCase.getQuestionStartExamQuestionGroup()
            result.let {
                questionsSize = it.questions.size
                nextQuestion()
            }
        }
    }

    fun increaseSuccess() {
        successCount++
    }

    fun checkForCorrect(answers: List<Answer>) {
        val correctAnswers = questionGroup?.questions?.first()?.answers?.filter { it.is_correct }
        if (correctAnswers!!.size == answers.filter { it.is_correct }.size) {
            increaseSuccess()
        } else {
            if (answers.count { it.is_correct } != 0 && answers.count { !it.is_correct } != 0) {
                // Show Case If correct
            } else {
                // Show Wrong Information
            }
        }
        nextQuestion()
    }

    private fun nextQuestion() {
        questionGroup?.let {
            step++
            questionGroup.questions.removeFirst()
            val lastQuestion = questionGroup.questions.firstOrNull()
            if (lastQuestion == null) {
                uiData.postValue(QuestionAction.ShowLastScreenAction)
            } else uiData.postValue(
                QuestionAction.ShowQuestionAction(
                    quiestion = lastQuestion,
                    questionsSize,
                    step,
                    successCount
                )
            )
            removeLastQuestion()
        }
    }

    private fun removeLastQuestion() {
        questionGroup?.questions?.remove(questionGroup.questions.last())
    }

}
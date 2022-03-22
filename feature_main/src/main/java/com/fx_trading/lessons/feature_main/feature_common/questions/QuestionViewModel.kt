package com.fx_trading.lessons.feature_main.feature_common.questions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fx_trading.lessons.domain.entities.quiz.Answer
import com.fx_trading.lessons.domain.entities.quiz.Question
import com.fx_trading.lessons.domain.entities.quiz.QuestionsGroup
import com.fx_trading.lessons.domain.usecases.QuestionUseCase
import com.fx_trading.lessons.utils.utils.Logger
import com.fx_trading.navigation.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class ResultChoices{
    AlmostSuccess, Wrong, Success
}

sealed class QuestionAction {
    data class ShowQuestionAction(
        val quiestion: Question,
        val questionSize: Int,
        val step: Int,
        val succesCount: Int
    ) : QuestionAction()
    data class ShowUserChoicesInfo(val result: ResultChoices, val userChoices: List<Answer>, val allAnswers: List<Answer>): QuestionAction()
    data class ShowLastScreenAction(val first_question: Boolean = false, val questionGroupID: Int,val  successAnswers: Int, val totalAnswers: Int, val totalQuestions: Int,val  successQuestions: Int) : QuestionAction()
    object ShowLoadingAction : QuestionAction()
    object ShowResultAction : QuestionAction()
}

class QuestionViewModel @Inject constructor(
    private val router: Router,
    var questionUseCase: QuestionUseCase
) : ViewModel() {

    private var successCount = 0
    private var errorCount = 0
    private var questionsSize = 0
    private val totalAnswers = 0
    private var step = 0
    private var questions = mutableListOf<Question>()
    private val successAnswers = 0

    private var questionGroup: QuestionsGroup? = null

    val uiData = MutableLiveData<QuestionAction>(QuestionAction.ShowLoadingAction)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            questionUseCase.getQuestionStartExamQuestionGroup().collect {
                questionGroup = it
                questionsSize = it.questions.size
                questions = it.questions
                nextQuestion()
            }
        }
    }



    fun increaseSuccess() {
        successCount++
        Logger.log("QuestionViewModel", "SuccessCount $successCount ErrorCount $errorCount")
    }

    fun increaseError(){
        errorCount++
        Logger.log("QuestionViewModel", "SuccessCount $successCount ErrorCount $errorCount")
    }

    fun checkForCorrect(answers: List<Answer>) {
        val correctAnswers = questions.getOrNull(step-1)?.answers?.filter { it.is_correct }
        if (answers.size == correctAnswers?.size && answers.map { it.text }.containsAll(correctAnswers.map{it.text})) {
            increaseSuccess()
            uiData.postValue(
                QuestionAction.ShowUserChoicesInfo(
                    result = ResultChoices.Success,
                    userChoices = answers,
                    allAnswers = questionGroup?.questions?.first()!!.answers
                )
            )
        } else {
            if (answers.count { it.is_correct } != 0 && answers.count { !it.is_correct } != 0) {
                uiData.postValue(
                    QuestionAction.ShowUserChoicesInfo(
                        result = ResultChoices.AlmostSuccess,
                        userChoices = answers,
                        allAnswers = questionGroup?.questions?.first()!!.answers
                    )
                )
            } else {
                uiData.postValue(
                    QuestionAction.ShowUserChoicesInfo(
                        result = ResultChoices.Wrong,
                        userChoices = answers,
                        allAnswers = questionGroup?.questions?.first()!!.answers
                    )
                )
            }
            increaseError()
        }
        removeLastQuestion()
    }

    fun nextQuestion() {
        questionGroup?.let {
            val lastQuestion = questions.getOrNull(step)
            step++
            if (lastQuestion == null) {
                questionGroup?.let { group->
                    uiData.postValue(
                        QuestionAction.ShowLastScreenAction(
                            first_question = true,
                            questionGroupID = group.id,
                            successQuestions = successCount,
                            totalQuestions = questionsSize,
                            totalAnswers = totalAnswers,
                            successAnswers = successAnswers
                        )
                    )
                }

            } else uiData.postValue(
                QuestionAction.ShowQuestionAction(
                    quiestion = lastQuestion,
                    questionsSize,
                    step,
                    successCount
                )
            )
        }
    }

    private fun removeLastQuestion() {
    }

}
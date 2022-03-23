package com.fx_trading.lessons.feature_main.feature_common.questions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fx_trading.common.State
import com.fx_trading.common.createState
import com.fx_trading.lessons.domain.entities.quiz.Answer
import com.fx_trading.lessons.domain.entities.quiz.Question
import com.fx_trading.lessons.domain.entities.quiz.QuestionsGroup
import com.fx_trading.lessons.domain.usecases.QuestionUseCase
import com.fx_trading.lessons.utils.utils.Logger
import com.fx_trading.navigation.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class ResultChoices {
    Wrong, Success
}

data class QuestionInfo(
    val question: Question?,
    val questionSize: Int,
    val step: Int,
    val successCount: Int,
    val errorCount: Int,
)

data class ShowUserChoicesInfo(
    val result: ResultChoices,
    val lastQuestion: Boolean,
    val userChoices: List<Answer>
)

class QuestionViewModel @Inject constructor(
    private val router: Router,
    var questionUseCase: QuestionUseCase
) : ViewModel() {

    private var questionsSize = 0
    private val totalAnswers = 0
    private var step = 0
    private var questions = mutableListOf<Question>()
    private var successAnswers = 0
    private var errorAnswers = 0

    private var questionGroup: QuestionsGroup? = null
        private set(value) {
            value?.let {
                questionsSize = it.questions.size
                questions = it.questions
                field = value
            }
        }


    init {
        viewModelScope.launch(Dispatchers.IO) {
            nextQuestion()
        }
    }

    fun getQuestions(id: Int?) = flow {
        emit(State.LoadingState)
        try {
            delay(500)
            questionGroup = if (id != null) {
                questionUseCase.getQuestionsGroup(id)
            } else questionUseCase.getStartQuestionGroup()
            questionGroup?.let {
                questions = it.questions
            }
            emit(createState(questionGroup))
        } catch (e: Exception) {
            Logger.log("ExampleViewModel", exception = e)
            emit(State.ErrorState(e))
        }
    }

    private fun increaseSuccess() {
        successAnswers++
        Logger.log("QuestionViewModel", "SuccessCount $successAnswers ErrorCount $errorAnswers")
    }

    private fun increaseError() {
        errorAnswers++
        Logger.log("QuestionViewModel", "SuccessCount $successAnswers ErrorCount $errorAnswers")
    }

   fun checkForCorrect(answers: List<Answer>) = flow<State<ShowUserChoicesInfo>> {
       try {
           val correctAnswers = questions.getOrNull(step - 1)?.answers?.filter { it.is_correct }
           if (answers.size == correctAnswers?.size && answers.map { it.text }.containsAll(correctAnswers.map { it.text })) {
               increaseSuccess()
               emit(State.DataState<ShowUserChoicesInfo>(ShowUserChoicesInfo(
                   result = ResultChoices.Success,
                   userChoices = answers,
                   lastQuestion = step == questionsSize
               )))
           } else {
               if (answers.count { it.is_correct } != 0 && answers.count { !it.is_correct } != 0) {
                   emit(State.DataState<ShowUserChoicesInfo>(ShowUserChoicesInfo(
                       result = ResultChoices.Wrong,
                       userChoices = answers,
                       lastQuestion = step == questionsSize
                   )))
               } else {
                   emit(State.DataState<ShowUserChoicesInfo>(ShowUserChoicesInfo(
                       result = ResultChoices.Wrong,
                       userChoices = answers,
                       lastQuestion = step == questionsSize
                   )))
               }
               increaseError()
           }
       } catch (e: Exception){
           Logger.log("QuestionViewModel", exception = e)
       }

    }

    fun nextQuestion() = flow<State<QuestionInfo>> {
        try {
            questionGroup?.let {
                step++
                emit(State.DataState<QuestionInfo>(QuestionInfo(
                    question = questions.getOrNull(step-1),
                    questionSize = questionsSize,
                    step = step,
                    successCount = successAnswers,
                    errorCount = errorAnswers
                ))
                )

            }
        } catch (e: Exception) {
            Logger.log("ExampleViewModel", exception = e)
            emit(State.ErrorState(e))
        }

    }

}
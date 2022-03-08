package com.fx_trading.lessons.feature_common.ui.questions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fx_trading.lessons.domain.entities.quiz.QuestionsGroup
import com.fx_trading.lessons.domain.usecases.QuestionUseCase
import com.fx_trading.navigation.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class QuestionViewModel @Inject constructor(
    private val router: Router,
    var questionUseCase: QuestionUseCase
) : ViewModel() {

    private var successCount = 0
    private var questionsSize = 0
    private var step = 1

    private val _data = MutableLiveData<QuestionsGroup?>()

    val data: LiveData<QuestionsGroup?>
        get() = _data

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val result = questionUseCase.getQuestionStartExamQuestionGroup()
            result.let {
                questionsSize = it.questions.size
                nextQuestion()
                _data.postValue(it)
            }
        }
    }

    fun increaseSuccess(){
        successCount++
    }

    fun nextQuestion(){
       _data.value?.let {
           it.questions.toMutableList().remove(it.questions.first())
            step++
        }
    }

}
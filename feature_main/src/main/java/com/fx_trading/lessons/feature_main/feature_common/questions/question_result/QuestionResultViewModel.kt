package com.fx_trading.lessons.feature_main.feature_common.questions.question_result

import androidx.lifecycle.ViewModel
import com.fx_trading.common.State
import com.fx_trading.lessons.domain.usecases.UserUseCase
import com.fx_trading.lessons.utils.utils.Logger
import data.DataStoreHelper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class QuestionResultViewModel @Inject constructor(val userUseCase: UserUseCase, val  dataStoreHelper: DataStoreHelper) : ViewModel() {

    fun saveExamResult(lessonID: Int, questionID: Int) = flow {
        emit(State.LoadingState)
        try {
            delay(500)
            dataStoreHelper.userID().collect {
              emit(State.DataState(userUseCase.saveLessonAndQuestionPassedToUserInfo(lessonID = lessonID, questionGroupID = questionID, it)))
            }
        } catch (e: Exception){
            Logger.log("ExampleViewModel", exception = e)
            emit(State.ErrorState(e))
        }
    }

}
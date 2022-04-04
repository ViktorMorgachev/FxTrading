package com.learning.feature_common.questions.question_result

import androidx.lifecycle.ViewModel
import com.learning.common.State
import com.learning.lessons.domain.usecases.UserInfoUseCase
import com.learning.lessons.utils.utils.Logger
import data.DataStoreHelper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class QuestionResultViewModel @Inject constructor(val userInfoUseCase: UserInfoUseCase, val  dataStoreHelper: DataStoreHelper) : ViewModel() {

    fun saveExamResult(lessonID: Int, questionID: Int) = flow {
        emit(State.LoadingState)
        try {
            delay(500)
            dataStoreHelper.userID().collect { userID->
              emit(State.DataState(userInfoUseCase.saveTesting(questionGroupID = questionID, userID, lessonID)))
            }
        } catch (e: Exception){
            Logger.log("ExampleViewModel", exception = e)
            emit(State.ErrorState(e))
        }
    }

}
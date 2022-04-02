package com.learning.lessons.feature_main.feature_common.questions.quiz_result


import androidx.lifecycle.ViewModel
import com.learning.common.State
import com.learning.lessons.data.pseudoDeviceID
import com.learning.lessons.domain.usecases.UserInfoUseCase
import data.DataStoreHelper
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FirstQuestionsResultViewModel @Inject constructor(
    private val userInfoUseCase: UserInfoUseCase,
    private val dataStoreHelper: DataStoreHelper
) : ViewModel() {

    fun saveUserResultToDatabase(questionGroupID: Int, level: Int)= flow<State<Boolean>> {
        val success = userInfoUseCase.saveFirstTesting(questionGroupID, level, null)
        if (success) {
            dataStoreHelper.examWasPassed(true)
            dataStoreHelper.saveDeviceID(pseudoDeviceID)
            userInfoUseCase.getUserIDByDeviceID()?.let {
                dataStoreHelper.saveUserID(it)
                emit(State.DataState(true))
            }
        }
        else emit(State.DataState(false))
    }

}
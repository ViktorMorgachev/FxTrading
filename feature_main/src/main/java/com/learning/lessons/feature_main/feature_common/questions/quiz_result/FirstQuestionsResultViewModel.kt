package com.learning.lessons.feature_main.feature_common.questions.quiz_result

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learning.lessons.data.pseudoDeviceID
import com.learning.lessons.domain.usecases.UserUseCase
import com.learning.navigation.Router
import data.DataStoreHelper
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class TotalResultAction {
    class EnableMainButton() : TotalResultAction()
}

class FirstQuestionsResultViewModel @Inject constructor(
    private val router: Router,
    private var userUseCase: UserUseCase,
    private val dataStoreHelper: DataStoreHelper
) : ViewModel() {

    val uiData = MutableLiveData<TotalResultAction>()

    fun saveUserResultToDatabase(questionGroupID: Int, level: Int) {
        viewModelScope.launch {
            val success = userUseCase.saveFirstResultToDatabase(questionGroupID, level, null)
            if (success) {
                dataStoreHelper.examWasPassed(true)
                dataStoreHelper.saveDeviceID(pseudoDeviceID)
                userUseCase.getUserIDByDeviceID()?.let {
                    dataStoreHelper.saveUserID(it)
                    uiData.postValue(TotalResultAction.EnableMainButton())
                }
            }
        }

    }

}
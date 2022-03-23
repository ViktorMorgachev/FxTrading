package com.fx_trading.lessons.feature_main.feature_common.questions.quiz_result

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fx_trading.lessons.data.pseudoDeviceID
import com.fx_trading.lessons.domain.usecases.UserUseCase
import com.fx_trading.navigation.Router
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

    fun saveUserResultToDatabase(questionGroupID: Int, level: Int, status: Int) {
        viewModelScope.launch {
            val success = userUseCase.saveResultToDatabase(questionGroupID, level, status, null)
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
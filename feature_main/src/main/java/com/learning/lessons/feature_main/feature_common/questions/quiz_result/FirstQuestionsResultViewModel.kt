package com.learning.lessons.feature_main.feature_common.questions.quiz_result

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learning.lessons.data.pseudoDeviceID
import com.learning.lessons.domain.usecases.UserInfoUseCase
import com.learning.navigation.Router
import data.DataStoreHelper
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class TotalResultAction {
    class EnableMainButton() : TotalResultAction()
}

class FirstQuestionsResultViewModel @Inject constructor(
    private val userInfoUseCase: UserInfoUseCase,
    private val dataStoreHelper: DataStoreHelper
) : ViewModel() {

    val uiData = MutableLiveData<TotalResultAction>()

    fun saveUserResultToDatabase(questionGroupID: Int, level: Int) {
        viewModelScope.launch {
            val success = async {userInfoUseCase.saveFirstTesting(questionGroupID, level, null)  }.await()
            if (success) {
                dataStoreHelper.examWasPassed(true)
                dataStoreHelper.saveDeviceID(pseudoDeviceID)
                userInfoUseCase.getUserIDByDeviceID()?.let {
                    dataStoreHelper.saveUserID(it)
                    uiData.postValue(TotalResultAction.EnableMainButton())
                }
            }
        }

    }

}
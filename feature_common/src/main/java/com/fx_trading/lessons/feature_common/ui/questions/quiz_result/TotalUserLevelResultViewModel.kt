package com.fx_trading.lessons.feature_common.ui.questions.quiz_result

import androidx.lifecycle.ViewModel
import com.fx_trading.lessons.domain.usecases.QuestionUseCase
import com.fx_trading.lessons.domain.usecases.UserUseCase
import com.fx_trading.navigation.Router
import data.DataStoreHelper
import javax.inject.Inject

class TotalUserLevelResultViewModel @Inject constructor(
    private val router: Router,
    private var userUseCase: UserUseCase,
    private val dataStoreHelper: DataStoreHelper
) : ViewModel() {

     fun saveUserResultToDatabase(questionGroupID: Int, level: Int, status: Int){


        // Есть ли в преференсах пользователь актуальный, если нет то
        // Проверить есть ли в базе пользователь с таким идентификатором устройства, если есть то получить пользователя по текущему ид устройства, сохранить
        // Если есть то просто Сохранить данные пользователя в базе по текущему ид пользователя

        userUseCase.saveResultToDatabase(questionGroupID, level, status, null)

    }

}
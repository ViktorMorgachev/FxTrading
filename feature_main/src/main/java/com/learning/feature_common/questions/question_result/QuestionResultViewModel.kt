package com.learning.feature_common.questions.question_result

import androidx.lifecycle.ViewModel
import com.learning.common.State
import com.learning.feature_common.questions.Flag
import com.learning.lessons.domain.usecases.UserInfoUseCase
import com.learning.lessons.utils.utils.Logger
import data.DataStoreHelper
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class QuestionResultViewModel @Inject constructor(
    val userInfoUseCase: UserInfoUseCase,
    val dataStoreHelper: DataStoreHelper
) : ViewModel() {

    fun saveExamResult(materialID: Int, questionID: Int, flag: String) = flow {
        emit(State.LoadingState)
        try {
            dataStoreHelper.userID().collect { userID ->
                when(flag){
                    Flag.Lesson.name -> {
                        emit(State.DataState(userInfoUseCase.saveTestingLesson(questionGroupID = questionID, userID, materialID)))
                    }
                    Flag.Article.name -> {
                        emit(State.DataState(userInfoUseCase.saveTestingArticle(questionGroupID = questionID, userID, materialID)))
                    }
                }
            }
        } catch (e: Exception) {
            Logger.log("QuestionResultViewModel", exception = e)
            emit(State.ErrorState(e))
        }
    }

}
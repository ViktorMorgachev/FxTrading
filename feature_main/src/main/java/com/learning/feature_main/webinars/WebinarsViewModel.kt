package com.learning.feature_main.webinars

import androidx.lifecycle.ViewModel
import com.learning.common.State
import com.learning.common.createState
import com.learning.lessons.domain.entities.webinar.Webinar
import com.learning.lessons.domain.usecases.WebinarsUseCase
import com.learning.lessons.utils.utils.Logger
import data.DataStoreHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WebinarsViewModel @Inject constructor(val webinarsUseCase: WebinarsUseCase,val  dataStoreHelper: DataStoreHelper) : ViewModel() {

    fun getWebinars() = flow {
        emit(State.LoadingState)
        try {
            val webinars = webinarsUseCase.getWebinars()
            emit(createState(webinars))
        } catch (e: Exception){
            Logger.log("WebinarsViewModel", exception = e)
            emit(State.ErrorState(e))
        }
    }

    suspend fun subscribeToWebinars(): MutableStateFlow<List<Webinar>> {
        return  webinarsUseCase.getWebinarsFlow()
    }

    fun likeWebinar(webinarID: Int) = flow<State<Webinar>> {
        try {
            dataStoreHelper.userID().collect {
                val success = webinarsUseCase.likeWebinar(webinarID = webinarID, userID = it.toInt())
                if (success){
                    emit(createState(webinarsUseCase.getWebinarByID(webinarID)))
                }
            }
        } catch (e: Exception){
            Logger.log("WebinarsViewModel", exception = e)
            emit(State.ErrorState(exception = e))
        }
    }

}
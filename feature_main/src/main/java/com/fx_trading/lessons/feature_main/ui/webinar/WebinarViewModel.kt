package com.fx_trading.lessons.feature_main.ui.webinar

import androidx.lifecycle.ViewModel
import com.fx_trading.common.State
import com.fx_trading.common.createState
import com.fx_trading.lessons.domain.entities.webinar.Webinar
import com.fx_trading.lessons.domain.usecases.WebinarsUseCase
import com.fx_trading.lessons.utils.utils.Logger
import data.DataStoreHelper
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WebinarViewModel @Inject constructor(val webinarsUseCase: WebinarsUseCase, val  dataStoreHelper: DataStoreHelper) : ViewModel() {

    fun getWebinarByID(webinarID: Int) = flow<State<Webinar>> {
        emit(State.LoadingState)
        try {
            emit(createState(webinarsUseCase.getWebinarByID(webinarID = webinarID)))
        } catch (e: Exception){
            Logger.log("ExampleViewModel", exception = e)
            emit(State.ErrorState(e))
        }
    }

    fun likeWebinar(webinarID: Int) = flow<State<Webinar>> {
        try {
            dataStoreHelper.userID().collect {
                val success = webinarsUseCase.likeWebinar(webinarID = webinarID, userID = it)
                if (success){
                    emit(createState(webinarsUseCase.getWebinarByID(webinarID)))
                }
            }
        } catch (e: Exception){
            Logger.log("WebinarViewModel", exception = e)
            emit(State.ErrorState(exception = e))
        }
    }

    fun dislikeWebinar(webinarID: Int)= flow<State<Webinar>> {
        try {
            dataStoreHelper.userID().collect {
                val success = webinarsUseCase.dislikeWebinar(webinarID = webinarID, userID = it)
                if (success){
                    emit(createState(webinarsUseCase.getWebinarByID(webinarID)))
                }
            }
        } catch (e: Exception){
            Logger.log("WebinarViewModel", exception = e)
            emit(State.ErrorState(exception = e))
        }
    }

}
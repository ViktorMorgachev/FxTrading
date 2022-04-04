package com.learning.feature_main.webinar

import androidx.lifecycle.ViewModel
import com.learning.common.State
import com.learning.common.createState
import com.learning.lessons.domain.entities.webinar.Webinar
import com.learning.lessons.domain.usecases.WebinarsUseCase
import com.learning.lessons.utils.utils.Logger
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
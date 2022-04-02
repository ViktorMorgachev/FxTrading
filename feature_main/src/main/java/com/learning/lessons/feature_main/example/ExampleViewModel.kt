package com.learning.lessons.feature_main.example

import androidx.lifecycle.ViewModel
import com.learning.common.State
import com.learning.lessons.utils.utils.Logger
import com.learning.navigation.Router
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ExampleViewModel  @Inject constructor(
    private val router: Router
) : ViewModel() {

    fun getData() = flow<State<Any>> {
        emit(State.LoadingState)
        try {
            delay(500)
            emit(State.DataState<Any>("Example data"))
        } catch (e: Exception){
            Logger.log("ExampleViewModel", exception = e)
            emit(State.ErrorState(e))
        }
    }

}
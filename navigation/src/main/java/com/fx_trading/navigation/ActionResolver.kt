package com.fx_trading.navigation

import android.app.Activity
import androidx.activity.ComponentActivity
import com.fx_trading.navigation.params.action.ActionParams

interface ActionResolver {

    fun registerResultListener(activity: ComponentActivity)

    fun execute(activity: Activity?, data: ActionParams)

}
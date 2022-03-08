package com.fx_trading.navigation

import android.app.Activity
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavHostController
import com.fx_trading.navigation.params.action.ActionParams
import com.fx_trading.navigation.params.screens.ScreenParams

interface Router {

    fun onCreate(activity: ComponentActivity)

    fun bind(activity: Activity)

    fun bind(controller: NavHostController, activity: Activity)

    fun navigate(data: ScreenParams, sharedElements: Map<Any, String>? = null)

    fun navigate(
        idContainer: Int,
        fragment: Fragment,
        data: ScreenParams,
        sharedElements: Map<Any, String>? = null
    )

    fun execute(data: ActionParams)

    fun setResultListener(key: String, listener: ResultListener)

    fun sendResult(key: String, data: Any?)

    fun back()

    fun getMainStartIntent(): Intent
}
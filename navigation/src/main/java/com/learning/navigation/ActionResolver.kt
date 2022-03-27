package com.learning.navigation

import android.app.Activity
import androidx.activity.ComponentActivity
import com.learning.navigation.params.action.ActionParams

interface ActionResolver {

    fun registerResultListener(activity: ComponentActivity)

    fun execute(activity: Activity?, data: ActionParams)
}
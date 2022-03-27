package com.learning.lessons.android.navigation

import android.app.Activity
import androidx.activity.ComponentActivity
import com.learning.navigation.ActionResolver
import com.learning.navigation.ResultContainer
import com.learning.navigation.params.action.ActionParams
import javax.inject.Inject

class ActionResolverImpl @Inject constructor(
    private val resultContainer: ResultContainer
) : ActionResolver {

    // list of launcher Описать конкретные случаи использования

    override fun registerResultListener(activity: ComponentActivity) {

    }

    override fun execute(activity: Activity?, data: ActionParams) {

    }



}
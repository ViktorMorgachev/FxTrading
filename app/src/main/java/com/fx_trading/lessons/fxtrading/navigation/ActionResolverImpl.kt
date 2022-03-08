package com.fx_trading.lessons.fxtrading.navigation

import android.app.Activity
import androidx.activity.ComponentActivity
import com.fx_trading.navigation.ActionResolver
import com.fx_trading.navigation.ResultContainer
import com.fx_trading.navigation.params.action.ActionParams
import javax.inject.Inject

class ActionResolverImpl @Inject constructor(
    private val resultContainer: ResultContainer
) : ActionResolver {

    // list of launcher Описать конкретные случаи использования

    override fun registerResultListener(activity: ComponentActivity) {

    }

    override fun execute(activity: Activity?, data: ActionParams) {

    }

    override fun startActivity(from: Activity, activity: Activity?) {

    }


}
package com.fx_trading.lessons.fxtrading

import android.app.Activity
import android.content.Intent
import com.fx_trading.lessons.feature_common.QuestionActivity
import com.fx_trading.lessons.feature_main.MainActivity


enum class Module {
    Onboarding, Main, Questions
}

object ModuleBinder {
    fun gotoToModule(module: Module, activity: Activity) {
        when (module) {
            Module.Main -> {
                activity.startActivity(Intent(activity, MainActivity::class.java))
            }
            Module.Questions ->{
                activity.startActivity(Intent(activity, QuestionActivity::class.java))
            }
        }

    }
}
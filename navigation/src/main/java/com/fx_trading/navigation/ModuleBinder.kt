package com.fx_trading.navigation

import android.app.Activity
import android.content.Intent
import com.fx_trading.navigation.activities.QuestionActivity
import com.fx_trading.navigation.activities.MainActivity


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
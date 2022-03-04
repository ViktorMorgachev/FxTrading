package com.fx_trading.lessons.feature_binder_module

import android.app.Activity
import android.content.Intent
import com.fx_trading.lessons.feature_main.MainActivity
import com.fx_trading.lessons.feature_questions.QuestionActivity

// Так как активности тоже лежат в стеке то думаю достаточно для каждой фичи своя активность

enum class Module {
    Onboarding, Questions, Main
}

object ModuleBinder {
    fun gotoToModule(module: Module, activity: Activity) {
        when (module) {
            Module.Questions -> {
                activity.startActivity(Intent(activity, QuestionActivity::class.java))
            }
            Module.Main -> {
                activity.startActivity(Intent(activity, MainActivity::class.java))
            }
        }

    }
}
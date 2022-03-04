package com.fx_trading.lessons.core.utils

import android.util.Log
import com.fx_trading.lessons.core.BuildConfig


enum class Type {
    Error, Debug, Info
}

object Logger {
    fun log(tag: String, message: String, type: Type = Type.Debug) {
        if (BuildConfig.DEBUG) {
            when (type) {
                Type.Debug -> Log.d(tag, message)
                Type.Error -> Log.e(tag, message)
                Type.Info -> Log.i(tag, message)
            }
        }
    }

    fun log(tag: String,  message: String?, exception: Throwable?) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, message, exception)
        }
    }

}
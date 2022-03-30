package com.learning.lessons.utils.utils

import android.util.Log


enum class Type {
    Error, Debug, Info
}

object Logger {
    fun log(tag: String, message: String, type: Type = Type.Debug) {
        when (type) {
            Type.Debug -> Log.d(tag, message)
            Type.Error -> Log.e(tag, message)
            Type.Info -> Log.i(tag, message)
        }
    }

    fun log(tag: String,  message: String? = null, exception: Throwable?) {
        Log.e(tag, message, exception)
    }

}
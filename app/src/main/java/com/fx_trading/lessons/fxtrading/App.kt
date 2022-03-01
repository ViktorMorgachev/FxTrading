package com.fx_trading.lessons.fxtrading

import android.app.Application
import android.content.Context
import com.fx_trading.lessons.fxtrading.di.component.AppComponent
import com.fx_trading.lessons.fxtrading.di.component.DaggerAppComponent

class App : Application() {


    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
    }
}

val Context.appComponent: AppComponent
    get() {
        return when (this) {
            is App -> appComponent
            else -> this.applicationContext.appComponent
        }
    }
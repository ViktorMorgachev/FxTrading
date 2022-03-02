package com.fx_trading.lessons.fxtrading

import android.app.Application
import android.content.Context
import com.fx_trading.lessons.fxtrading.di.component.AppComponent
import com.fx_trading.lessons.fxtrading.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App : Application(), HasAndroidInjector {


    lateinit var appComponent: AppComponent

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

}

val Context.appComponent: AppComponent
    get() {
        return when (this) {
            is App -> appComponent
            else -> this.applicationContext.appComponent
        }
    }
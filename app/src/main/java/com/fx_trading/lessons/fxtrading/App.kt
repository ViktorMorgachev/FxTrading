package com.fx_trading.lessons.fxtrading

import android.app.Application
import android.content.Context
import com.fx_trading.lessons.domain.usecases.QuestionUseCase
import com.fx_trading.lessons.fxtrading.di.component.AppComponent
import com.fx_trading.lessons.fxtrading.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

// По завершению каждой фичи модуля, котрые основываются на новых активностях, переходить к предыдущей активности

class App : Application() {

    lateinit var appComponent: AppComponent

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>


    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().application(this).build()
    }


}

val Context.appComponent: AppComponent
    get() {
        return when (this) {
            is App -> appComponent
            else -> this.applicationContext.appComponent
        }
    }
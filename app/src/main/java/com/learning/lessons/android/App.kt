package com.learning.lessons.android

import com.learning.lessons.android.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

// По завершению каждой фичи модуля, котрые основываются на новых активностях, переходить к предыдущей активности

class App : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.factory().create(this)
}
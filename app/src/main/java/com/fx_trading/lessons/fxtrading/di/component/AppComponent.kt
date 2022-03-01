package com.fx_trading.lessons.fxtrading.di.component

import android.app.Application
import com.fx_trading.lessons.fxtrading.App
import com.fx_trading.lessons.fxtrading.di.module.AppModule
import com.fx_trading.lessons.fxtrading.di.module.uiBuilder.ActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    ActivityModule::class,
    AndroidSupportInjectionModule::class
])
interface AppComponent: AndroidInjector<Application> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: App): AppComponent
    }

    override fun inject(app: Application) // Let Dagger know your Application class with root dispatching injector
}

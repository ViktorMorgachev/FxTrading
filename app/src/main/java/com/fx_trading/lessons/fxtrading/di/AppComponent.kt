package com.fx_trading.lessons.fxtrading.di

import android.app.Application
import com.fx_trading.lessons.fxtrading.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope
import javax.inject.Singleton

@[AppScope Component(modules = [AppModule::class])]
interface AppComponent {
    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}

@Scope
annotation class AppScope
package com.fx_trading.lessons.fxtrading.di.component

import android.app.Application
import com.fx_trading.lessons.fxtrading.di.module.AppModule
import com.fx_trading.lessons.fxtrading.di.module.uiBuilder.ActivityBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    ActivityBuilder::class,
    AndroidSupportInjectionModule::class
])
interface AppComponent {
    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}

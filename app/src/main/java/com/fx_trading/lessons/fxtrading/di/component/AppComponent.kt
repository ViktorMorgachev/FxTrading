package com.fx_trading.lessons.fxtrading.di.component

import android.app.Application
import com.fx_trading.lessons.fxtrading.App
import com.fx_trading.lessons.fxtrading.di.module.AppModule
import com.fx_trading.lessons.fxtrading.di.module.FireBaseModule
import com.fx_trading.lessons.fxtrading.di.module.RepositoryBindModule
import com.fx_trading.lessons.fxtrading.di.module.RepositoryProvidesModule
import com.fx_trading.lessons.fxtrading.di.module.navigation.NavigationModule
import com.fx_trading.lessons.fxtrading.di.module.navigation.screen_modules.OnboardingScreenMapModule
import com.fx_trading.lessons.fxtrading.di.module.navigation.screen_modules.QuestionsScreenMapModule
import com.fx_trading.lessons.fxtrading.di.module.uiBuilder.ActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AppModule::class,
        FireBaseModule::class,
        ActivityModule::class,
        NavigationModule::class,
        OnboardingScreenMapModule::class,
        QuestionsScreenMapModule::class,
        AndroidSupportInjectionModule::class,
        RepositoryBindModule::class,
        RepositoryProvidesModule::class
    ]
)
interface AppComponent: AndroidInjector<App> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }

}

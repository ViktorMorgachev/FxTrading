package com.fx_trading.lessons.fxtrading.di.component

import android.app.Application
import com.fx_trading.lessons.fxtrading.App
import com.fx_trading.lessons.fxtrading.di.module.AppModule
import com.fx_trading.lessons.fxtrading.di.module.navigation.NavigationModule
import com.fx_trading.lessons.fxtrading.di.module.navigation.NavigationScreenMapModule
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
        ActivityModule::class,
        NavigationModule::class,
        NavigationScreenMapModule::class,
        AndroidSupportInjectionModule::class
//        RepositoryBindModule::class,
//        RepositoryProvidesModule::class,
//        UseCaseProvidesModule::class
    ]
)
interface AppComponent: AndroidInjector<App> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }

}

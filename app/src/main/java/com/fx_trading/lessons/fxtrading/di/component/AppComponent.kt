package com.fx_trading.lessons.fxtrading.di.component

import com.fx_trading.lessons.fxtrading.App
import com.fx_trading.lessons.fxtrading.di.module.AppModule
import com.fx_trading.lessons.fxtrading.di.module.RepositoryBindModule
import com.fx_trading.lessons.fxtrading.di.module.RepositoryProvidesModule
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
        AndroidSupportInjectionModule::class,
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        fun application(@BindsInstance application: App): Builder
        fun build(): AppComponent
    }

    override fun inject(app: App) // Let Dagger know your Application class with root dispatching injector
}

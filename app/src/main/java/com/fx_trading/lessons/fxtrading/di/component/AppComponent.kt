package com.fx_trading.lessons.fxtrading.di.component

import com.fx_trading.lessons.feature_common.di.deps.FeatureCommonDeps
import com.fx_trading.lessons.fxtrading.App
import com.fx_trading.lessons.fxtrading.di.module.AppModule
import com.fx_trading.lessons.fxtrading.di.module.RepositoryBindModule
import com.fx_trading.lessons.fxtrading.di.module.RepositoryProvidesModule
import com.fx_trading.lessons.fxtrading.di.module.UseCaseProvidesModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AppModule::class,
        RepositoryBindModule::class,
        RepositoryProvidesModule::class,
        UseCaseProvidesModule::class
    ]
)
interface AppComponent: FeatureCommonDeps {

    @Component.Builder
    interface Builder {
        fun application(@BindsInstance application: App): Builder
        fun build(): AppComponent
    }

}

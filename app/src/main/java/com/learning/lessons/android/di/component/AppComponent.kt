package com.learning.lessons.android.di.component

import android.app.Application
import com.learning.lessons.android.App
import com.learning.lessons.android.di.module.AppModule
import com.learning.lessons.android.di.module.FireBaseModule
import com.learning.lessons.android.di.module.RepositoryBindModule
import com.learning.lessons.android.di.module.RepositoryProvidesModule
import com.learning.lessons.android.di.module.navigation.NavigationModule
import com.learning.lessons.android.di.module.navigation.screen_modules.OnboardingScreenMapModule
import com.learning.lessons.android.di.module.navigation.screen_modules.QuestionsScreenMapModule
import com.learning.lessons.android.di.module.uiBuilder.ActivityModule
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

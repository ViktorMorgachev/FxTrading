package com.learning.lessons.android.di.module.navigation

import com.learning.lessons.android.navigation.ActionResolverImpl
import com.learning.lessons.android.navigation.RouterImpl
import com.learning.lessons.android.navigation.ScreenResolverImpl
import com.learning.navigation.ActionResolver
import com.learning.navigation.Router
import com.learning.navigation.ScreenResolver
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface NavigationModule {

    @Binds
    @Singleton
    fun ScreenResolverImpl.bindScreenResolver(): ScreenResolver

    @Binds
    @Singleton
    fun ActionResolverImpl.bindActionResolver(): ActionResolver

    @Binds
    @Singleton
    fun RouterImpl.bindRouter(): Router

}
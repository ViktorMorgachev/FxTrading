package com.fx_trading.lessons.fxtrading.di.module.navigation

import com.fx_trading.lessons.fxtrading.navigation.ActionResolverImpl
import com.fx_trading.lessons.fxtrading.navigation.RouterImpl
import com.fx_trading.lessons.fxtrading.navigation.ScreenResolverImpl
import com.fx_trading.navigation.ActionResolver
import com.fx_trading.navigation.Router
import com.fx_trading.navigation.ScreenResolver
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
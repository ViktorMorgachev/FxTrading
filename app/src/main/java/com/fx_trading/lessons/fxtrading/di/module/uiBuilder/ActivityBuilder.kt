package com.fx_trading.lessons.fxtrading.di.module.uiBuilder

import com.fx_trading.lessons.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityProvider::class])
    fun provide(): MainActivity

}
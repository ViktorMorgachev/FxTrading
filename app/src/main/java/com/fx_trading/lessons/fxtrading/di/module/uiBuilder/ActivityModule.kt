package com.fx_trading.lessons.fxtrading.di.module.uiBuilder

import com.fx_trading.lessons.feature_onboarding.OnBoardingActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {

    @ContributesAndroidInjector(modules = [OnBoardingBuilderProvider::class])
    fun contributeMainActivity(): OnBoardingActivity


}
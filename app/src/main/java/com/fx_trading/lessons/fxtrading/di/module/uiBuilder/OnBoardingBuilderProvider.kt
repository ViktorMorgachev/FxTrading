package com.fx_trading.lessons.fxtrading.di.module.uiBuilder

import com.fx_trading.lessons.feature_onboarding.SplashFragment
import com.fx_trading.lessons.feature_onboarding.start_test.StartTestFragment
import com.fx_trading.lessons.feature_onboarding.ui.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface OnBoardingBuilderProvider {

    @ContributesAndroidInjector
    fun onBoardingFragmentFirst() : OnBoardingFragmentFirst

    @ContributesAndroidInjector
    fun onBoardingFragmentSecond() : OnBoardingFragmentSecond

    @ContributesAndroidInjector
    fun onBoardingFragmentThird() : OnBoardingFragmentThird

    @ContributesAndroidInjector
    fun onBoardingFragmentFour() : OnBoardingFragmentFour

    @ContributesAndroidInjector
    fun onBoardingFragmentFive() : OnBoardingFragmentFive

    @ContributesAndroidInjector
    fun splashFragment() : SplashFragment

    @ContributesAndroidInjector
    fun startTestFragment() : StartTestFragment
}
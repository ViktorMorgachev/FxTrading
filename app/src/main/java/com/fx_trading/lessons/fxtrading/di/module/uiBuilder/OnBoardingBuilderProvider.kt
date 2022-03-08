package com.fx_trading.lessons.fxtrading.di.module.uiBuilder

import com.fx_trading.lessons.feature_onboarding.SplashFragment
import com.fx_trading.lessons.feature_onboarding.start_test.StartTestFragment
import com.fx_trading.lessons.feature_onboarding.ui.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface OnBoardingBuilderProvider {

    @ContributesAndroidInjector
    fun onBoardingFragmentFirst() : OnBoardingFirstFragment

    @ContributesAndroidInjector
    fun onBoardingFragmentSecond() : OnBoardingSecondFragment

    @ContributesAndroidInjector
    fun onBoardingFragmentThird() : OnBoardingThirdFragment

    @ContributesAndroidInjector
    fun onBoardingFragmentFour() : OnBoardingFourFragment

    @ContributesAndroidInjector
    fun onBoardingFragmentFive() : OnBoardingFiveFragment

    @ContributesAndroidInjector
    fun splashFragment() : SplashFragment

    @ContributesAndroidInjector
    fun startTestFragment() : StartTestFragment
}
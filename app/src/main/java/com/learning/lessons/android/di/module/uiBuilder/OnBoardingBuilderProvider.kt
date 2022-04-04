package com.learning.lessons.android.di.module.uiBuilder

import com.learning.feature_main.splash.SplashFragment
import com.learning.feature_onboarding.OnBoardingFirstFragment
import com.learning.feature_onboarding.OnBoardingSecondFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface OnBoardingBuilderProvider {

    @ContributesAndroidInjector
    fun onBoardingFragmentFirst() : OnBoardingFirstFragment

    @ContributesAndroidInjector
    fun onBoardingFragmentSecond() : OnBoardingSecondFragment

    @ContributesAndroidInjector
    fun splashFragment() : SplashFragment

}
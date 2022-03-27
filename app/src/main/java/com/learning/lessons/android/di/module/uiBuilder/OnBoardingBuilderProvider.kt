package com.learning.lessons.android.di.module.uiBuilder

import com.learning.lessons.feature_main.feature_onboarding.main.*
import com.learning.lessons.feature_main.ui.splash.SplashFragment
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
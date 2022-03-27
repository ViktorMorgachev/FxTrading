package com.learning.lessons.android.di.module.navigation.screen_modules

import com.learning.lessons.android.R
import com.learning.lessons.android.di.module.navigation.ScreenKey
import com.learning.navigation.NavigationData
import com.learning.navigation.bundle.BundleCreator
import com.learning.navigation.params.screens.onboarding.*
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class OnboardingScreenMapModule {

    @IntoMap
    @Provides
    @ScreenKey(SplashFirstScreenParams::class)
    fun splashFirst() : NavigationData = NavigationData(
        navId = R.id.action_splashFragment_to_onBoardingFragmentFirst,
        bundleCreator = BundleCreator.empty()
    )

    @IntoMap
    @Provides
    @ScreenKey(SplashWelcomeScreenParams::class)
    fun splashWelcome() : NavigationData = NavigationData(
        navId = R.id.action_splashFragment_to_welcomeFragment,
        bundleCreator = BundleCreator.empty()
    )

    @IntoMap
    @Provides
    @ScreenKey(FirstSecondScreenParams::class)
    fun firstSecond() : NavigationData = NavigationData(
        navId = R.id.action_onBoardingFragmentFirst_to_OnBoardingFragmentSecond,
        bundleCreator = BundleCreator.empty()
    )

    @IntoMap
    @Provides
    @ScreenKey(FirstWelcomeScreenParams::class)
    fun firstWelcome() : NavigationData = NavigationData(
        navId = R.id.action_onBoardingFragmentFirst_to_welcomeFragment,
        bundleCreator = BundleCreator.empty()
    )

    @IntoMap
    @Provides
    @ScreenKey(SecondWelcomeScreenParams::class)
    fun secondWelcome() : NavigationData = NavigationData(
        navId = R.id.action_onBoardingFragmentSecond_to_WelcomeFragment,
        bundleCreator = BundleCreator.empty()
    )

}
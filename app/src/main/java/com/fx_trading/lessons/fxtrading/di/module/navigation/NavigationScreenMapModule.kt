package com.fx_trading.lessons.fxtrading.di.module.navigation

import com.fx_trading.lessons.fxtrading.R
import com.fx_trading.navigation.NavigationData
import com.fx_trading.navigation.bundle.BundleCreator
import com.fx_trading.navigation.params.screens.onboarding.*
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class NavigationScreenMapModule {

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
    @ScreenKey(FitstSecondScreenParams::class)
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

    @IntoMap
    @Provides
    @ScreenKey(SecondThirdScreenParams::class)
    fun secondThird() : NavigationData = NavigationData(
        navId = R.id.action_onBoardingFragmentSecondto_OnBoardingFragmentThird,
        bundleCreator = BundleCreator.empty()
    )

    @IntoMap
    @Provides
    @ScreenKey(ThirdWelcomeScreenParams::class)
    fun thirdWelcome() : NavigationData = NavigationData(
        navId = R.id.action_onBoardingFragmentThird_to_WelcomeFragment,
        bundleCreator = BundleCreator.empty()
    )

    @IntoMap
    @Provides
    @ScreenKey(ThirdFourScreenParams::class)
    fun thirdFour() : NavigationData = NavigationData(
        navId = R.id.action_onBoardingFragmentThird_to_OnBoardingFragmentFour,
        bundleCreator = BundleCreator.empty()
    )

    @IntoMap
    @Provides
    @ScreenKey(FourFiveScreenParams::class)
    fun fourFive() : NavigationData = NavigationData(
        navId = R.id.action_onBoardingFragmentFour_to_OnBoardingFragmentFive,
        bundleCreator = BundleCreator.empty()
    )

    @IntoMap
    @Provides
    @ScreenKey(FourWelcomeScreenParams::class)
    fun fourWelcome() : NavigationData = NavigationData(
        navId = R.id.action_onBoardingFragmentFour_to_WelcomeFragment,
        bundleCreator = BundleCreator.empty()
    )

    @IntoMap
    @Provides
    @ScreenKey(FiveStartTestScreenParams::class)
    fun fiveStartTest() : NavigationData = NavigationData(
        navId = R.id.action_onBoardingFragmentFive_to_StartTestFragment,
        bundleCreator = BundleCreator.empty()
    )

}
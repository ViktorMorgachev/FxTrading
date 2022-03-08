package com.fx_trading.lessons.fxtrading.di.module.uiBuilder

import com.fx_trading.navigation.activities.QuestionActivity
import com.fx_trading.lessons.feature_onboarding.OnBoardingActivity
import com.fx_trading.navigation.activities.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {

    @ContributesAndroidInjector(modules = [OnBoardingBuilderProvider::class])
    fun contributeOnBoardingActivity(): OnBoardingActivity

    @ContributesAndroidInjector(modules = [QuestionBuilderProvider::class])
    fun contributeQuestionActivity(): QuestionActivity

    @ContributesAndroidInjector(modules = [MainBuilderProvider::class])
    fun contributeMainActivity(): MainActivity

}
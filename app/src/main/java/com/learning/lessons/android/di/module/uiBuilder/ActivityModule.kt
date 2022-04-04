package com.learning.lessons.android.di.module.uiBuilder

import com.learning.feature_main.MainActivity
import com.learning.feature_onboarding.OnBoardingActivity
import com.learning.feature_common.questions.QuestionActivity
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
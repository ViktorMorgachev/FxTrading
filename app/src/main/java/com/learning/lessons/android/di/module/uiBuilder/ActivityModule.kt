package com.learning.lessons.android.di.module.uiBuilder

import com.learning.lessons.feature_main.activities.MainActivity
import com.learning.lessons.feature_main.activities.OnBoardingActivity
import com.learning.lessons.feature_main.activities.QuestionActivity
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
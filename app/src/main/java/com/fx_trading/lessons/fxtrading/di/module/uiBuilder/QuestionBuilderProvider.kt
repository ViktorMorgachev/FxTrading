package com.fx_trading.lessons.fxtrading.di.module.uiBuilder

import com.fx_trading.lessons.feature_common.ui.questions.QuestionsFragment
import com.fx_trading.lessons.feature_common.ui.test.TestFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface QuestionBuilderProvider {

    @ContributesAndroidInjector
    fun questionsFragment() : QuestionsFragment

    @ContributesAndroidInjector
    fun questionTestFragment() : TestFragment

}
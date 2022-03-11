package com.fx_trading.lessons.fxtrading.di.module.navigation.screen_modules

import com.fx_trading.lessons.fxtrading.R
import com.fx_trading.lessons.fxtrading.di.module.navigation.ScreenKey
import com.fx_trading.navigation.NavigationData
import com.fx_trading.navigation.bundle.BundleCreator
import com.fx_trading.navigation.params.screens.onboarding.*
import com.fx_trading.navigation.params.screens.questions.QuestionToPreResult
import com.fx_trading.navigation.params.screens.questions.ToPreResultToTotalResult
import com.fx_trading.navigation.params.screens.questions.ToPreResultToUserLevelResult
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class QuestionsScreenMapModule {

    @IntoMap
    @Provides
    @ScreenKey(QuestionToPreResult::class)
    fun questionToPreResult() : NavigationData = NavigationData(
        navId = R.id.action_questionsFragment_to_lastQuestionAnsweredFragment,
        bundleCreator = BundleCreator.empty()
    )


    @IntoMap
    @Provides
    @ScreenKey(ToPreResultToTotalResult::class)
    fun preResultToTotalResult() : NavigationData = NavigationData(
        navId = R.id.action_lastQuestionAnsweredFragment_to_totalQuestionsResult,
        bundleCreator = BundleCreator.empty()
    )

    @IntoMap
    @Provides
    @ScreenKey(ToPreResultToUserLevelResult::class)
    fun preResultToUserLevelResult() : NavigationData = NavigationData(
        navId = R.id.action_lastQuestionAnsweredFragment_to_totalUserLevelResultFragment,
        bundleCreator = BundleCreator.empty()
    )

}
package com.fx_trading.lessons.feature_common.di.component

import com.fx_trading.lessons.feature_common.di.deps.FeatureCommonDeps
import com.fx_trading.lessons.feature_common.ui.questions.QuestionsFragment
import dagger.Component

@Component(dependencies = [FeatureCommonDeps::class])
internal interface FeatureCommonComponent {

    fun inject(questionsFragment: QuestionsFragment)

    @Component.Builder
    interface Builder{
        fun deps(deps: FeatureCommonDeps): Builder
        fun build(): FeatureCommonComponent
    }

}
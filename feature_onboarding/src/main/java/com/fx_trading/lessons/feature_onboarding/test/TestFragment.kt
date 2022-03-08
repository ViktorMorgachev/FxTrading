package com.fx_trading.lessons.feature_onboarding.test

import com.fx_trading.lessons.domain.usecases.QuestionUseCase
import com.fx_trading.lessons.feature_onboarding.R
import moxy.MvpAppCompatFragment
import javax.inject.Inject

class TestFragment : MvpAppCompatFragment(R.layout.fragment_test) {

    @Inject
    lateinit var questionUseCase: QuestionUseCase

    override fun onResume() {
        super.onResume()
        questionUseCase.test()

    }
}
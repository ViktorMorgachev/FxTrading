package com.fx_trading.lessons.feature_onboarding

import com.fx_trading.lessons.core.BaseActivity
import com.fx_trading.lessons.core.utils.Logger

class OnBoardingActivity : BaseActivity() {

    override fun initLayout() {
        setContentView(R.layout.activity_onboarding)
    }

    override fun onDestroy() {
        super.onDestroy()
        Logger.log("OnBoardingActivity", "onDestroy()")
    }
}
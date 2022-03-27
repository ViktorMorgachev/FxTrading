package com.learning.lessons.feature_main.activities

import com.learning.lessons.features.R
import com.learning.navigation.BaseActivity

class OnBoardingActivity : BaseActivity(R.id.nav_host_fragment_onboarding) {

    override fun initLayout() {
        setContentView(R.layout.activity_onboarding)
    }
}
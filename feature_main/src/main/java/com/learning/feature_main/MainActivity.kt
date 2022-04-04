package com.learning.feature_main

import com.learning.lessons.features.R
import com.learning.navigation.BaseActivity

class MainActivity : BaseActivity(R.id.nav_host_fragment_main) {

    override fun initLayout() {
        setContentView(R.layout.activity_main)
    }
}



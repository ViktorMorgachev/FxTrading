package com.fx_trading.lessons.feature_onboarding

import android.os.Bundle
import android.os.PersistableBundle
import com.fx_trading.lessons.core.BaseActivity
import com.fx_trading.lessons.core.utils.Logger
import com.fx_trading.navigation.Router
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class OnBoardingActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        router.bind(this)
        router.onCreate(this)
    }

    override fun onResume() {
        super.onResume()
        router.bind(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        Logger.log("OnBoardingActivity", "onDestroy()")
    }
}
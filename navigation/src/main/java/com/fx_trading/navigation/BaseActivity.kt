package com.fx_trading.navigation

import android.os.Bundle
import com.fx_trading.navigation.Router
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


abstract class BaseActivity(protected val fragmentContainerID: Int) : DaggerAppCompatActivity() {

    @Inject
    lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayout()
        router.bind(this, fragmentContainerID)
        router.onCreate(this)
    }

    open fun initLayout(){}

    override fun onResume() {
        super.onResume()
        router.bind(this, fragmentContainerID)
    }
}
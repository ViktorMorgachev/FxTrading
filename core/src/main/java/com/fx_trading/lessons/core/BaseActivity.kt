package com.fx_trading.lessons.core

import android.os.Bundle
import androidx.annotation.IntegerRes
import com.fx_trading.navigation.Router
import dagger.android.AndroidInjection
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


abstract class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayout()
        router.bind(this)
        router.onCreate(this)
    }

    open fun initLayout(){}

    override fun onResume() {
        super.onResume()
        router.bind(this)
    }
}
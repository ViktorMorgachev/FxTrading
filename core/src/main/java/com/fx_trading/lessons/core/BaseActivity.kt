package com.fx_trading.lessons.core

import android.os.Bundle
import android.os.PersistableBundle
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity


abstract class BaseActivity : DaggerAppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        initLayout()
        actionRouter()
    }

    open fun initLayout(){}
    open fun actionRouter(){}

}
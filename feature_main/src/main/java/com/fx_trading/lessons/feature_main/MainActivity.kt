package com.fx_trading.lessons.feature_main

import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.fx_trading.lessons.core.BaseActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
class MainActivity : BaseActivity() {

    override fun initLayout() {
        setContentView(R.layout.activity_main)
        val navController = this.findNavController(R.id.nav_host_fragment_main)
        val navView: BottomNavigationView = findViewById(R.id.bottom_nav_view)
        navView.setupWithNavController(navController)
    }

}
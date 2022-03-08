package com.fx_trading.navigation.activities

import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.fx_trading.navigation.BaseActivity
import com.fx_trading.navigation.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseActivity(R.id.nav_host_fragment_main) {

    override fun initLayout() {
        setContentView(R.layout.activity_main)
        val navController = this.findNavController(R.id.nav_host_fragment_main)
        val navView: BottomNavigationView = findViewById(R.id.bottom_nav_view)
        navView.setupWithNavController(navController)
    }

}
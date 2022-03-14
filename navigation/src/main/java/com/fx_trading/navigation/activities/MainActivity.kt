package com.fx_trading.navigation.activities

import androidx.viewpager.widget.ViewPager
import com.fx_trading.navigation.BaseActivity
import com.fx_trading.navigation.R
import com.fx_trading.navigation.viewPagerAdapter.DemoCollectionPagerAdapter

class MainActivity : BaseActivity(R.id.nav_host_fragment_main) {

    override fun initLayout() {
        setContentView(R.layout.activity_main)
        val demoCollectionPagerAdapter = DemoCollectionPagerAdapter(supportFragmentManager)
        val viewPager = findViewById<ViewPager>(R.id.view_pager)
        viewPager.adapter = demoCollectionPagerAdapter
    }

}
package com.fx_trading.lessons.feature_main.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import by.kirich1409.viewbindingdelegate.viewBinding
import com.fx_trading.lessons.feature_main.ui.courses.CoursesFragment
import com.fx_trading.lessons.feature_main.ui.lessons.LessonsFragment
import com.fx_trading.lessons.feature_main.ui.webinars.WebinarsFragment
import com.fx_trading.lessons.features.R
import com.fx_trading.lessons.features.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.android.support.DaggerAppCompatActivity

val tabsNameRes = listOf<Int>(R.string.lessons, R.string.courses,R.string.webinars)

class MainActivity : DaggerAppCompatActivity(R.layout.activity_main) {

    private val viewBinding: ActivityMainBinding by viewBinding()

    private val viewPagerAdapter by lazy {
        object : FragmentStateAdapter(this) {
            override fun getItemCount() = 3

            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> {
                        LessonsFragment()
                    }
                    1 -> {
                        CoursesFragment()
                    }
                    2 -> WebinarsFragment()
                    else -> LessonsFragment()
                }
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(viewBinding) {
            viewPager.adapter = viewPagerAdapter
            val tabLayoutStrategy = TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.text = getString(tabsNameRes[position])
            }
            TabLayoutMediator(tabs, viewPager, true, true, tabLayoutStrategy).attach()
        }
    }
}



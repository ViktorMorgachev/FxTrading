package com.fx_trading.lessons.feature_main.ui.main

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fx_trading.lessons.core.BaseFragment
import com.fx_trading.lessons.feature_main.ui.courses.CoursesFragment
import com.fx_trading.lessons.feature_main.ui.lessons.LessonsFragment
import com.fx_trading.lessons.feature_main.ui.webinars.WebinarsFragment
import com.fx_trading.lessons.features.R
import com.fx_trading.lessons.features.databinding.FragmentMainBinding
import com.google.android.material.tabs.TabLayoutMediator

val tabsNameRes = listOf<Int>(R.string.lessons, R.string.courses, R.string.webinars)

class MainFragment : BaseFragment<FragmentMainBinding>() {


    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding = FragmentMainBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            viewPager.adapter = object : FragmentStateAdapter(this@MainFragment) {
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
            val tabLayoutStrategy = com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.text = getString(tabsNameRes[position])
            }
            TabLayoutMediator(tabs, viewPager, true, true, tabLayoutStrategy).attach()
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }
}
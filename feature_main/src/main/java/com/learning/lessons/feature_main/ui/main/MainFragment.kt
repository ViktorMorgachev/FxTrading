package com.learning.lessons.feature_main.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.learning.lessons.core.BaseFragment
import com.learning.lessons.features.R
import com.learning.lessons.features.databinding.FragmentMainBinding

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
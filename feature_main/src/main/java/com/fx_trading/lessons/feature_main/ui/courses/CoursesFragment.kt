package com.fx_trading.lessons.features.ui.courses

import android.view.LayoutInflater
import android.view.ViewGroup
import com.fx_trading.lessons.core.BaseFragment
import com.fx_trading.lessons.features.databinding.FragmentCoursesBinding

class CoursesFragment : BaseFragment<FragmentCoursesBinding>(), CoursesView {
    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCoursesBinding = FragmentCoursesBinding::inflate
}
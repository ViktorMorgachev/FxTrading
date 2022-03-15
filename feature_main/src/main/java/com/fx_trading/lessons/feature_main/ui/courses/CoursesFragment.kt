package com.fx_trading.lessons.feature_main.ui.courses

import android.view.LayoutInflater
import android.view.ViewGroup
import com.fx_trading.lessons.core.BaseFragment
import com.fx_trading.lessons.features.databinding.FragmentCoursesBinding
import com.fx_trading.lessons.features.ui.courses.CoursesView

class CoursesFragment : BaseFragment<FragmentCoursesBinding>(), CoursesView {
    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCoursesBinding = FragmentCoursesBinding::inflate
}
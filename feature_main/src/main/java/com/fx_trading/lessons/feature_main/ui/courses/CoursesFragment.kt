package com.fx_trading.lessons.feature_main.ui.courses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fx_trading.lessons.core.BaseFragment
import com.fx_trading.lessons.feature_main.R
import com.fx_trading.lessons.feature_main.databinding.FragmentCoursesBinding
import com.fx_trading.lessons.feature_main.databinding.FragmentDifferentBinding
import com.fx_trading.lessons.feature_main.ui.different.DifferentView

class CoursesFragment : BaseFragment<FragmentCoursesBinding>(), CoursesView {
    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCoursesBinding = FragmentCoursesBinding::inflate
}
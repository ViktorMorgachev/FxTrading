package com.learning.lessons.feature_main.ui.courses

import android.view.LayoutInflater
import android.view.ViewGroup
import com.learning.lessons.core.BaseFragment
import com.learning.lessons.features.databinding.FragmentCoursesBinding
import com.learning.lessons.features.ui.courses.CoursesView

class CoursesFragment : BaseFragment<FragmentCoursesBinding>(), CoursesView {
    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCoursesBinding = FragmentCoursesBinding::inflate
}
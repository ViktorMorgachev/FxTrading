package com.fx_trading.lessons.features.ui.lessons

import android.view.LayoutInflater
import android.view.ViewGroup
import com.fx_trading.lessons.core.BaseFragment
import com.fx_trading.lessons.features.databinding.FragmentLessonsBinding
import com.fx_trading.navigation.Router
import javax.inject.Inject

class LessonsFragment:  BaseFragment<FragmentLessonsBinding>() {
    @Inject
    lateinit var router: Router
    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLessonsBinding = FragmentLessonsBinding::inflate

}
package com.learning.lessons.features.ui.different

import android.view.LayoutInflater
import android.view.ViewGroup
import com.learning.lessons.core.BaseFragment
import com.learning.lessons.features.databinding.FragmentDifferentBinding

class DifferentFragment : BaseFragment<FragmentDifferentBinding>(), DifferentView {
    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDifferentBinding = FragmentDifferentBinding::inflate
}
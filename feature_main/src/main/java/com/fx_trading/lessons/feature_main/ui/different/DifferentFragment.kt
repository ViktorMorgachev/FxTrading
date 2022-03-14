package com.fx_trading.lessons.features.ui.different

import android.view.LayoutInflater
import android.view.ViewGroup
import com.fx_trading.lessons.core.BaseFragment
import com.fx_trading.lessons.features.databinding.FragmentDifferentBinding

class DifferentFragment : BaseFragment<FragmentDifferentBinding>(), DifferentView {
    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDifferentBinding = FragmentDifferentBinding::inflate
}
package com.fx_trading.lessons.feature_main.ui.different

import android.view.LayoutInflater
import android.view.ViewGroup
import com.fx_trading.lessons.core.BaseFragment
import com.fx_trading.lessons.feature_main.databinding.FragmentDifferentBinding
import com.fx_trading.navigation.Router
import javax.inject.Inject

class DifferentFragment : BaseFragment<FragmentDifferentBinding>(), DifferentView {
    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDifferentBinding = FragmentDifferentBinding::inflate
}
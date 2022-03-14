package com.fx_trading.lessons.features.ui.webinars

import android.view.LayoutInflater
import android.view.ViewGroup
import com.fx_trading.lessons.core.BaseFragment
import com.fx_trading.lessons.features.databinding.FragmentWebinarsBinding

class WebinarsFragment:  BaseFragment<FragmentWebinarsBinding>(), WebinarsView {
    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentWebinarsBinding = FragmentWebinarsBinding::inflate
}
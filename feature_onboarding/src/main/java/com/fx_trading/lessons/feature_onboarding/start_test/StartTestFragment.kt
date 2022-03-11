package com.fx_trading.lessons.feature_onboarding.start_test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fx_trading.lessons.core.BaseFragment
import com.fx_trading.lessons.feature_onboarding.databinding.FragmentStartTestBinding
import com.fx_trading.navigation.Module
import com.fx_trading.navigation.ModuleBinder
import com.fx_trading.navigation.Router
import javax.inject.Inject


class StartTestFragment : BaseFragment<FragmentStartTestBinding>() {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentStartTestBinding = FragmentStartTestBinding::inflate

    @Inject
    lateinit var router: Router

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            skipButton.setOnClickListener {
                ModuleBinder.gotoToModule(Module.Main, requireActivity())
            }
            startTest.setOnClickListener {
                ModuleBinder.gotoToModule(Module.Questions, requireActivity())
            }
        }
    }
}
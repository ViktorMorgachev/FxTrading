package com.fx_trading.lessons.feature_main.feature_onboarding.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fx_trading.lessons.core.BaseFragment
import com.fx_trading.lessons.features.databinding.FragmentOnBoardingFourBinding
import com.fx_trading.navigation.Router
import com.fx_trading.navigation.params.screens.onboarding.FourFiveScreenParams
import javax.inject.Inject


class OnBoardingFourFragment : BaseFragment<FragmentOnBoardingFourBinding>() {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentOnBoardingFourBinding = FragmentOnBoardingFourBinding::inflate

    @Inject
    lateinit var router: Router

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            buttonNext.setOnClickListener {
                router.navigate(FourFiveScreenParams)
            }
        }
    }
}
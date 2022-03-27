package com.learning.lessons.feature_main.feature_onboarding.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.learning.lessons.core.BaseFragment
import com.learning.lessons.features.databinding.FragmentOnBoardingThirdBinding
import com.learning.navigation.Router
import javax.inject.Inject


class OnBoardingThirdFragment : BaseFragment<FragmentOnBoardingThirdBinding>() {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentOnBoardingThirdBinding =
        FragmentOnBoardingThirdBinding::inflate

    @Inject
    lateinit var router: Router

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            buttonNext.setOnClickListener {
                router.navigate(ThirdFourScreenParams)
            }
        }
    }
}
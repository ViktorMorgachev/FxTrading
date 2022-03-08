package com.fx_trading.lessons.feature_onboarding.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.fx_trading.lessons.feature_onboarding.R
import com.fx_trading.lessons.feature_onboarding.databinding.FragmentOnBoardingFiveBinding


class OnBoardingFragmentFive : Fragment(R.layout.fragment_on_boarding_five) {

    private val viewBinding: FragmentOnBoardingFiveBinding by viewBinding(FragmentOnBoardingFiveBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewBinding){
            buttonFinish.setOnClickListener {
                requireView().findNavController().navigate(OnBoardingFragmentFiveDirections.actionOnBoardingFragmentFiveToStartTestFragment())
              //  ModuleBinder.gotoToModule(module = Module.Main, activity = this@OnBoardingFragmentFive.requireActivity())
            }
        }
    }
}
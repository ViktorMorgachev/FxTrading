package com.fx_trading.lessons.feature_onboarding.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fx_trading.lessons.core.BaseFragment
import com.fx_trading.navigation.Router
import com.fx_trading.navigation.params.screens.onboarding.FiveStartTestScreenParams
import javax.inject.Inject
import com.fx_trading.lessons.feature_onboarding.databinding.FragmentOnBoardingFiveBinding as Binding


class OnBoardingFiveFragment : BaseFragment<Binding>() {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> Binding =
        Binding::inflate

    @Inject
    lateinit var router: Router

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            buttonFinish.setOnClickListener {
                router.navigate(FiveStartTestScreenParams)
//                requireView().findNavController().navigate(OnBoardingFragmentFiveDirections.actionOnBoardingFragmentFiveToStartTestFragment())
              //  ModuleBinder.gotoToModule(module = Module.Main, activity = this@OnBoardingFragmentFive.requireActivity())
            }
        }
    }
}